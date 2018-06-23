package grupodobaralho.topfood_android.ui.login.view;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
