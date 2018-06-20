package grupodobaralho.topfood_android.ui.Cadastro.Model;

public interface CadastroInteractor {

    interface OnCadastroFinishedListener {
        void onNomeCompletoError();
        void onEmailError();
        void onPasswordError();
        void onSoccess();
    }

    void cadastro(String nomeCompleto, String email, String password, OnCadastroFinishedListener listener);

}
