package grupodobaralho.topfood_android.Cadastro.Presenter;

public interface CadastroPresenter {

    void validateSignUp(String nomeCompleto, String email, String password, String confirmPassword);
    boolean verifyConfirmPasswordError(String password, String confrimPassword);
    void onDestroy();

}
