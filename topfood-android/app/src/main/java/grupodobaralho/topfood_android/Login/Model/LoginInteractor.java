package grupodobaralho.topfood_android.Login.Model;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginInteractor {

    interface OnLoginFinishedLisener {
        void onEmailError();
        void onPasswordError();
        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedLisener listener);

}
