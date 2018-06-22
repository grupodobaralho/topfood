package grupodobaralho.topfood_android.ui.login.model;

import android.util.Log;

import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.AuthResponse;
import grupodobaralho.topfood_android.data.localStorage.UserBusiness;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements grupodobaralho.topfood_android.ui.login.model.LoginInteractor {

    //TODO: Fazer tratamento de erro (username e password vazios)

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {

        AuthRequest authRquest = new AuthRequest(username, password);

        //A retrofit instance that uses the UserEP Interface
        Call<AuthResponse> call = RetrofitInstance.retrofitCreate().authUser(authRquest);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.errorBody() != null) {
                    Log.d("RESPONDE NULL", "= NULL");
                    return;
                }
                String token = response.body().getAccess_token();
                UserBusiness.getInstance().updateAccessToken(token);
                Log.d("LOGADO", UserBusiness.getInstance().getAccessToken());
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
