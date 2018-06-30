package grupodobaralho.topfood_android.ui.signUp.presenter;

public interface ISignUpPresenter {

    interface OnCadastroFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onUsernameOrPasswordAlreadyRegistered();
        void onApiError();
        void onSuccess();
    }

    void validateSignUp(String email, String password, String confirmPassword);
    boolean verifyConfirmPasswordError(String password, String confrimPassword);
    void onDestroy();

}
