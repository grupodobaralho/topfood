package grupodobaralho.topfood_android.ui.signUp.presenter;

import grupodobaralho.topfood_android.ui.signUp.model.CadastroInteractor;
import grupodobaralho.topfood_android.ui.signUp.view.CadastroView;

public class CadastroPresenterImpl implements CadastroPresenter, CadastroInteractor.OnCadastroFinishedListener {

    private CadastroView cadastroView;
    private CadastroInteractor cadastroInteractor;

    private static CadastroPresenterImpl instance;

    private CadastroPresenterImpl(CadastroView cadastroView, CadastroInteractor cadastroInteractor) {
        this.cadastroView = cadastroView;
    }

    public static synchronized CadastroPresenterImpl getInstance(CadastroView cadastroView, CadastroInteractor cadastroInteractor) {
        if (instance == null)
            instance = new CadastroPresenterImpl(cadastroView, cadastroInteractor);

        return instance;
    }

    @Override
    public void validateSignUp(String nomeCompleto, String email, String password, String confirmPassword) {
        if (cadastroView != null) {
            cadastroView.showProgress();

            if (verifyConfirmPasswordError(password, confirmPassword))
                cadastroInteractor.cadastro(nomeCompleto, email, password, this);
        }
    }

    @Override
    public boolean verifyConfirmPasswordError(String password, String confrimPassword) {

        if(password.equals(confrimPassword))
            return true;

        cadastroView.verifyPasswordError();
        cadastroView.hideProgress();
        return false;
    }

    @Override
    public void onDestroy() {
        cadastroView = null;
    }

    @Override
    public void onNomeCompletoError() {
        if (cadastroView != null) {
            cadastroView.setNomeCompletoError();
            cadastroView.hideProgress();
        }
    }

    @Override
    public void onEmailError() {
        if (cadastroView != null) {
            cadastroView.setEmailError();
            cadastroView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (cadastroView != null) {
            cadastroView.setPasswordError();
            cadastroView.hideProgress();
        }
    }


    @Override
    public void onSoccess() {
        if (cadastroView != null)
            cadastroView.navigateToHome();
    }
}
