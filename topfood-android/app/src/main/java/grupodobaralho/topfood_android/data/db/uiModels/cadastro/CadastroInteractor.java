package grupodobaralho.topfood_android.data.db.uiModels.cadastro;

public interface CadastroInteractor {

    interface OnCadastroFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onSoccess();
    }

    void cadastro(String email, String password, OnCadastroFinishedListener listener);

}
