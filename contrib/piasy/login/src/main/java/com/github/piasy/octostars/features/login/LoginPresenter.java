/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Piasy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.piasy.octostars.features.login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.github.piasy.bootstrap.base.model.net.AuthInterceptor;
import com.github.piasy.oauth3.github.model.GitHubUser;
import com.github.piasy.octostars.PrefKeys;
import com.github.piasy.yamvp.dagger2.ActivityScope;
import com.github.piasy.yamvp.rx.YaRxPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;
import javax.inject.Inject;
import timber.log.Timber;

@ActivityScope
class LoginPresenter extends YaRxPresenter<LoginView>
        implements FirebaseAuth.AuthStateListener, OnCompleteListener<AuthResult> {

    private final Activity mActivity;
    private final AuthInterceptor mAuthInterceptor;
    private final SharedPreferences mPreferences;
    private final FirebaseAuth mFirebaseAuth;

    @Inject
    LoginPresenter(final Activity activity, final AuthInterceptor authInterceptor,
            final SharedPreferences preferences) {
        super();
        mActivity = activity;
        mAuthInterceptor = authInterceptor;
        mPreferences = preferences;

        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    void onActivityStart() {
        mFirebaseAuth.addAuthStateListener(this);
    }

    void onActivityStop() {
        mFirebaseAuth.removeAuthStateListener(this);
    }

    void onGitHubOAuthSuccess(final String token, final GitHubUser gitHubUser) {
        Timber.d("LoginPresenter::onGitHubOAuthSuccess " + token);

        getView().showProgress();
        mPreferences.edit().putString(PrefKeys.GITHUB_TOKEN, token).apply();
        mAuthInterceptor.updateAuth(token);

        final AuthCredential credential = GithubAuthProvider.getCredential(token);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(mActivity, this);
    }

    @Override
    public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        Timber.d("LoginPresenter::onAuthStateChanged " + currentUser);
        if (currentUser == null) {
            getView().login();
        } else {
            getView().loginSuccess();
        }
    }

    @Override
    public void onComplete(@NonNull final Task<AuthResult> task) {
        Timber.d("LoginPresenter::onComplete " + task.isSuccessful());
        if (!task.isSuccessful()) {
            Timber.e(task.getException());
            getView().loginFail();
        }
    }
}
