package grupodobaralho.topfood_android.ui.signUp.view;

import grupodobaralho.topfood_android.ui.login.view.ILoginView;

public interface ISignUpView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void setResponseError();

    void setApiError();

    void navigateToHome();

    void onSuccessSignUp(String mensagem);

    void verifyPasswordError();
}
