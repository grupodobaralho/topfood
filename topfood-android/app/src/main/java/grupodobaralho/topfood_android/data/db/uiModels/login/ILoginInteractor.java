package grupodobaralho.topfood_android.data.db.uiModels.login;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

import grupodobaralho.topfood_android.ui.login.presenter.ILoginPresenter;

public interface ILoginInteractor {

    void login(final String username, final String password, final ILoginPresenter.OnLoginFinishedListener listener);

}
