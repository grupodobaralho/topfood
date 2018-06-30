package grupodobaralho.topfood_android.ui.login.presenter;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface ILoginPresenter {

    interface OnLoginFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onInvalidUsernameOrPassword();
        void onApiError();
        void onSuccess();
    }

    void validateCredentials(String username, String password);
    void onDestroy();

}
