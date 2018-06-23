package grupodobaralho.topfood_android.ui.login.presenter;

import grupodobaralho.topfood_android.data.db.uiModels.login.LoginInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.login.LoginInteractorImpl;
import grupodobaralho.topfood_android.ui.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor; 

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
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
    public void onSuccess() {
        if (loginView != null)
            loginView.hideProgress();
            loginView.navigateToHome();
    }
}
