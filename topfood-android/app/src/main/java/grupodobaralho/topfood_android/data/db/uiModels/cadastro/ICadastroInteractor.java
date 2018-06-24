package grupodobaralho.topfood_android.data.db.uiModels.cadastro;

public interface ICadastroInteractor {

    interface OnCadastroFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onUsernameOrPasswordAlreadyRegistered();
        void onApiError();
        void onSuccess();
    }

    void cadastro(final String email, final String password, final OnCadastroFinishedListener listener);

}
