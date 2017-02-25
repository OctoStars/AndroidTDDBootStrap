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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.github.piasy.bootstrap.base.utils.ToastUtil;
import com.github.piasy.oauth3.github.GitHubOAuth;
import com.github.piasy.oauth3.github.model.GitHubUser;
import com.github.piasy.octostars.BootstrapActivity;
import com.github.piasy.octostars.BootstrapApp;
import com.github.piasy.octostars.RouteTable;
import javax.inject.Inject;
import timber.log.Timber;

@Route(RouteTable.LOGIN)
public class LoginActivity extends BootstrapActivity<LoginComponent>
        implements LoginView, GitHubOAuth.Listener {

    @Inject
    LoginPresenter mPresenter;
    @Inject
    ToastUtil mToastUtil;

    private LoginComponent mLoginComponent;
    private GitHubOAuth mGitHubOAuth;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.attachView(this);

        mGitHubOAuth = GitHubOAuth.builder()
                .clientId(BuildConfig.GITHUB_CLIENT_ID)
                .clientSecret(BuildConfig.GITHUB_CLIENT_SECRET)
                .scope("public_repo")
                .redirectUrl(BuildConfig.GITHUB_OAUTH_REDIRECT_URL)
                .build();

        mProgress = new ProgressDialog(this);
        mProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.onActivityStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mProgress.hide();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.onActivityStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mProgress.dismiss();
        mPresenter.detachView();
        mPresenter.onDestroy();
    }

    @Override
    protected void initializeDi() {
        mLoginComponent = DaggerLoginComponent.builder()
                .appComponent(BootstrapApp.get().appComponent())
                .loginModule(new LoginModule(this))
                .build();
        mLoginComponent.inject(this);
    }

    @Override
    public LoginComponent getComponent() {
        return mLoginComponent;
    }

    @Override
    public void onSuccess(final String token, final GitHubUser gitHubUser) {
        mPresenter.onGitHubOAuthSuccess(token, gitHubUser);
    }

    @Override
    public void onFail(final String error) {
        loginFail();
    }

    @Override
    public void login() {
        mGitHubOAuth.authorize(this);
    }

    @Override
    public void loginSuccess() {
        Timber.d("LoginActivity::loginSuccess");

        Router.build(RouteTable.TRENDING)
                .go(this);
        finish();
    }

    @Override
    public void loginFail() {
        mToastUtil.toast(R.string.login_fail_hint);
    }

    @Override
    public void showProgress() {
        mProgress.show();
    }
}
