package grupodobaralho.topfood_android.Login.Model;

public class LoginInteractorImpl implements LoginInteractor{


    @Override
    public void login(String username, String password, OnLoginFinishedLisener listener) {
        // Implmentar comunicacao com o banco
            // caso email vazio -> listener.onEmailError();
            // caso password vazio -> listener.onPasswordError();
            // caso tudo correto -> onSuccess();
    }
}
