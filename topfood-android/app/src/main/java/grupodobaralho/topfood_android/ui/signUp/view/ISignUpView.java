package grupodobaralho.topfood_android.ui.signUp.view;

import grupodobaralho.topfood_android.ui.login.view.ILoginView;

public interface ISignUpView extends ILoginView {

    void setNomeCompletoError();
    void verifyPasswordError();
}
