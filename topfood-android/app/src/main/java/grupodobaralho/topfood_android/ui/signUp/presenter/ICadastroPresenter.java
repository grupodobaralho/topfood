package grupodobaralho.topfood_android.ui.signUp.presenter;

public interface ICadastroPresenter {

    void validateSignUp(String email, String password, String confirmPassword);
    boolean verifyConfirmPasswordError(String password, String confrimPassword);
    void onDestroy();

}
