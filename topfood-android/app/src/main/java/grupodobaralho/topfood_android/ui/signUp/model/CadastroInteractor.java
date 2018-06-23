package grupodobaralho.topfood_android.ui.signUp.model;

public interface CadastroInteractor {

    interface OnCadastroFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onSoccess();
    }

    void cadastro(String email, String password, OnCadastroFinishedListener listener);

}
