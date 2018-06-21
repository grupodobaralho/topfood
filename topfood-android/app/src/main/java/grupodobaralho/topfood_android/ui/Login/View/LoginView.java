package grupodobaralho.topfood_android.ui.login.view;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setEmailError();

    void setPasswordError();

    void navigateToHome();
}
