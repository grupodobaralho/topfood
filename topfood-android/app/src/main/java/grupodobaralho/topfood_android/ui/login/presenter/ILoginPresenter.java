package grupodobaralho.topfood_android.ui.login.presenter;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface ILoginPresenter {

    void validateCredentials(String username, String password);
    void onDestroy();

}
