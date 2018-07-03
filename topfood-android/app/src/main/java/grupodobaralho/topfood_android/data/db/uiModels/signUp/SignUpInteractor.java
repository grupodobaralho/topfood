package grupodobaralho.topfood_android.data.db.uiModels.signUp;

import grupodobaralho.topfood_android.data.db.model.AuthResponse;
import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.SignUpResponse;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.signUp.presenter.ISignUpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpInteractor implements ISignUpInteractor {

    private ISignUpPresenter.OnCadastroFinishedListener listener;

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

        this.listener = listener;

        final AuthRequest authRquest = new AuthRequest(username, password);

        //A retrofit instance that uses the API_EndPoint Interface
        Call<SignUpResponse> call = RetrofitInstance.retrofitCreate().signUphUser(authRquest);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.code() != 200) {
                    listener.onUsernameOrPasswordAlreadyRegistered();
                    return;
                }

                listener.onSuccessSignUp();

                Call<AuthResponse> callLogin = RetrofitInstance.retrofitCreate().authUser(authRquest);

                callLogin.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        if (response.code() != 200) {
                            listener.onApiError();
                            return;
                        }

                        String token = response.body().getAccess_token();
                        String userid = response.body().getId();
                        UserBusiness.getInstance().updateAccessToken(token, userid);
                        listener.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        listener.onApiError();
                    }
                });
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                listener.onApiError();
            }
        });
    }

}
