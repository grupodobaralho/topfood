package grupodobaralho.topfood_android.ui.login.model;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
