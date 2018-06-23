package grupodobaralho.topfood_android.ui.signUp.presenter;

public interface CadastroPresenter {

    void validateSignUp(String email, String password, String confirmPassword);
    boolean verifyConfirmPasswordError(String password, String confrimPassword);
    void onDestroy();

}
