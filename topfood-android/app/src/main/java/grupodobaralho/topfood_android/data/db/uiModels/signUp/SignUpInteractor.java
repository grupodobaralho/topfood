package grupodobaralho.topfood_android.data.db.uiModels.signUp;

import android.widget.Toast;

import grupodobaralho.topfood_android.TopfoodApplication;
import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.SignUpResponse;
import grupodobaralho.topfood_android.data.db.uiModels.login.ILoginInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.login.LoginInteractor;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.signUp.presenter.ISignUpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpInteractor implements ISignUpInteractor {

    private ILoginInteractor loginInteractor;

    @Override
    public void cadastro(final String username, final String password, final ISignUpPresenter.OnCadastroFinishedListener listener) {

        if(username == null || username.isEmpty()) {
            listener.onEmailError();
            return;
        }

        if(password == null || password.isEmpty()) {
            listener.onPasswordError();
            return;
        }

        AuthRequest authRquest = new AuthRequest(username, password);

        //A retrofit instance that uses the API_EndPoint Interface
        Call<SignUpResponse> call = RetrofitInstance.retrofitCreate().signUphUser(authRquest);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.code() != 200) {
                    listener.onUsernameOrPasswordAlreadyRegistered();
                    return;
                }
                Boolean success = response.body().isSuccess();

                if (success) {
                    Toast.makeText(TopfoodApplication.getTopfoodApplicationContext(), "Usuario " + username + " cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                    loginInteractor = new LoginInteractor();
                    loginInteractor.login(username, password, null);
                }

                // Espera o loggar para realizar a transicao.
                if (!UserBusiness.getInstance().isLogged()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                listener.onSuccess();
                return;
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                listener.onApiError();
            }
        });
    }

}
