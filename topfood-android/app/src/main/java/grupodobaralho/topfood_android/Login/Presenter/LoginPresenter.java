package grupodobaralho.topfood_android.Login.Presenter;

/* Interface retirada de https://github.com/antoniolg/androidmvp */

public interface LoginPresenter {

    void validateCredentials(String username, String password);
    void onDestroy();

}
