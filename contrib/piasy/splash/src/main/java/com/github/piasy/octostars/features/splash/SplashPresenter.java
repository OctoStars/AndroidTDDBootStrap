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

package com.github.piasy.octostars.features.splash;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.github.piasy.bootstrap.base.model.net.AuthInterceptor;
import com.github.piasy.octostars.PrefKeys;
import com.github.piasy.yamvp.dagger2.ActivityScope;
import com.github.piasy.yamvp.rx.YaRxPresenter;
import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Inject;

/**
 * Created by Piasy{github.com/Piasy} on 20/09/2016.
 */

@ActivityScope
class SplashPresenter extends YaRxPresenter<SplashView> implements FirebaseAuth.AuthStateListener {

    private final AuthInterceptor mAuthInterceptor;
    private final SharedPreferences mPreferences;
    private final FirebaseAuth mFirebaseAuth;

    @Inject
    SplashPresenter(final AuthInterceptor authInterceptor, final SharedPreferences preferences) {
        super();
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

    @Override
    public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
        String githubToken = mPreferences.getString(PrefKeys.GITHUB_TOKEN, "");
        if (firebaseAuth.getCurrentUser() == null) {
            getView().login();
        } else if (TextUtils.isEmpty(githubToken)) {
            firebaseAuth.signOut();
            getView().login();
        } else {
            mAuthInterceptor.updateAuth(githubToken);
            getView().alreadyLoggedIn();
        }
    }
}
