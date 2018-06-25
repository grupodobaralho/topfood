package grupodobaralho.topfood_android.data.db.uiModels.login;

import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.AuthResponse;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements ILoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {

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
        Call<AuthResponse> call = RetrofitInstance.retrofitCreate().authUser(authRquest);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.code() != 200) {
                    listener.onInvalidUsernameOrPassword();
                    return;
                }

                String token = response.body().getAccess_token();
                UserBusiness.getInstance().updateAccessToken(token);

                if (listener == null)
                    return;
                else
                    listener.onSuccess();

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                listener.onApiError();
            }
        });
    }
}
