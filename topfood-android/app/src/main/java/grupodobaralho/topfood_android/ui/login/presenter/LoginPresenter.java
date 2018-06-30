package grupodobaralho.topfood_android.ui.login.presenter;

import grupodobaralho.topfood_android.data.db.uiModels.login.ILoginInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.login.LoginInteractor;
import grupodobaralho.topfood_android.ui.login.view.ILoginView;

public class LoginPresenter implements ILoginPresenter, ILoginPresenter.OnLoginFinishedListener {

    private ILoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractor();
    }

    @Override
    public void validateCredentials(String email, String password) {
        if (loginView != null)
            loginView.showProgress();

        loginInteractor.login(email, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onEmailError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onInvalidUsernameOrPassword() {
        if (loginView != null) {
            loginView.setResponseError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onApiError() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.setApiError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.navigateToHome();
        }
    }
}
