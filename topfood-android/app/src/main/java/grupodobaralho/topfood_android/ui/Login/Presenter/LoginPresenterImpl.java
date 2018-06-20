package grupodobaralho.topfood_android.ui.Login.Presenter;

import grupodobaralho.topfood_android.ui.Login.Model.LoginInteractor;
import grupodobaralho.topfood_android.ui.Login.View.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor; 

    // Variavel de verificacao singleton
    private static LoginPresenterImpl instance;

    // Utilizacao do padrao singleton
    private LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    // Metodo do padrao singleton getInstance
    public static synchronized LoginPresenterImpl getInstance(LoginView loginView, LoginInteractor loginInteractor) {
        if (instance == null)
            instance = new LoginPresenterImpl(loginView, loginInteractor);

        return instance;
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
            loginView.setEmailError();
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
            loginView.navigateToHome();
    }
}
