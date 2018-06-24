package grupodobaralho.topfood_android.ui.signUp.presenter;

import grupodobaralho.topfood_android.data.db.uiModels.cadastro.ICadastroInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.cadastro.CadastroInteractor;
import grupodobaralho.topfood_android.ui.signUp.view.ICadastroView;

public class CadastroPresenter implements ICadastroPresenter, ICadastroInteractor.OnCadastroFinishedListener {

    private ICadastroView cadastroView;
    private ICadastroInteractor cadastroInteractor;


    public CadastroPresenter(ICadastroView cadastroView) {
        this.cadastroView = cadastroView;
        this.cadastroInteractor = new CadastroInteractor();
    }

    @Override
    public void validateSignUp(String email, String password, String confirmPassword) {
        if (cadastroView != null) {
            cadastroView.showProgress();

            if (verifyConfirmPasswordError(password, confirmPassword))
                cadastroInteractor.cadastro(email, password, this);
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
    public void onEmailError() {
        if (cadastroView != null) {
            cadastroView.setUsernameError();
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
            cadastroView.hideProgress();
            cadastroView.navigateToHome();
    }
}
