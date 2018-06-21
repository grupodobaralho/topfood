package grupodobaralho.topfood_android.ui.login.model;

import android.util.Log;

import java.util.List;

import grupodobaralho.topfood_android.data.db.endPoint.UserEP;
import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.AuthResponse;
import grupodobaralho.topfood_android.data.db.model.User;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor{

    private List<User> teste;
    //A retrofit instance that uses the UserEP Interface
    private UserEP service = RetrofitInstance.getRetrofitInstance().create(UserEP.class);

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {

        AuthRequest authRquest = new AuthRequest(username, password);

        Call<AuthResponse> call = service.authUser(authRquest);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.errorBody() != null) {
                    Log.d("RESPONDE NULL", "= NULL");
                    return;
                }

                String token = response.body().getAccess_token();

//                AccessToken accessToken = response.body();
//                UserBusiness.getInstance().updateAccessToken(accessToken.getValue(), accessToken.getUserId());
//                Log.d("LOGADO", accessToken.getValue());
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("Sem conexão com a API.")
//                        .setPositiveButton("Ok", null);
//                builder.create().show();
                Log.d("Deu RUIMM", "Ruim demais");
            }
        });


    }
}
