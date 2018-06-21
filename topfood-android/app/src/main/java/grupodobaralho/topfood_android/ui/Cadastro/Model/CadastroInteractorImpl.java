package grupodobaralho.topfood_android.ui.Cadastro.Model;

public class CadastroInteractorImpl implements CadastroInteractor {

    @Override
    public void cadastro(String nomeCompleto, String email, String password, OnCadastroFinishedListener listener) {
        // Implmentar comunicacao com o banco
        // caso nome vazio -> listener.onNomeCompletoError();
        // caso email vazio -> listener.onEmailError();
        // caso password vazio -> listener.onPasswordError();
        // caso tudo correto -> onSuccess();
    }

}
