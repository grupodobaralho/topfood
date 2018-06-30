package grupodobaralho.topfood_android.ui.signUp.presenter;

import grupodobaralho.topfood_android.data.db.uiModels.signUp.ISignUpInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.signUp.SignUpInteractor;
import grupodobaralho.topfood_android.ui.signUp.view.ISignUpView;

public class SignUpPresenter implements ISignUpPresenter, ISignUpPresenter.OnCadastroFinishedListener {

    private ISignUpView cadastroView;
    private ISignUpInteractor cadastroInteractor;


    public SignUpPresenter(ISignUpView cadastroView) {
        this.cadastroView = cadastroView;
        this.cadastroInteractor = new SignUpInteractor();
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
    public void onUsernameOrPasswordAlreadyRegistered() {
        if (cadastroView != null) {
            cadastroView.setResponseError();
            cadastroView.hideProgress();
        }
    }

    @Override
    public void onApiError() {
        if (cadastroView != null) {
            cadastroView.setApiError();
            cadastroView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (cadastroView != null)
            cadastroView.hideProgress();
            cadastroView.navigateToHome();
    }
}
