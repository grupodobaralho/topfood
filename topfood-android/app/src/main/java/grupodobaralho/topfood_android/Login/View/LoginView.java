package grupodobaralho.topfood_android.Login.View;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setEmailError();

    void setPasswordError();

    void navigateToHome();
}