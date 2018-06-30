package grupodobaralho.topfood_android.data.db.uiModels.signUp;

import grupodobaralho.topfood_android.ui.signUp.presenter.ISignUpPresenter;

public interface ISignUpInteractor {

    void cadastro(final String email, final String password, final ISignUpPresenter.OnCadastroFinishedListener listener);

}
