package grupodobaralho.topfood_android.data.db.uiModels.login;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onSuccess();
    }

    void login(final String username, final String password, final OnLoginFinishedListener listener);

}
