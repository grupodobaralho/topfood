package grupodobaralho.topfood_android.data.db.uiModels.cadastro;

import android.util.Log;
import android.widget.Toast;

import grupodobaralho.topfood_android.TopfoodApplication;
import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.SignUpResponse;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.data.db.uiModels.login.LoginInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.login.LoginInteractorImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroInteractorImpl implements CadastroInteractor {

    private LoginInteractor loginInteractor;

    @Override
    public void cadastro(String username, String password, OnCadastroFinishedListener listener) {
        // Implmentar comunicacao com o banco
        // caso nome vazio -> listener.onNomeCompletoError();
        // caso email vazio -> listener.onEmailError();
        // caso password vazio -> listener.onPasswordError();
        // caso tudo correto -> onSuccess();

        final AuthRequest authRquest = new AuthRequest(username, password);

        //A retrofit instance that uses the UserEP Interface
        Call<SignUpResponse> call = RetrofitInstance.retrofitCreate().signUphUser(authRquest);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.errorBody() != null) {
                    Log.d("RESPONDE NULL", "= NULL");
                    return;
                }
                Boolean success = response.body().isSuccess();

                if (success) {
                    Toast.makeText(TopfoodApplication.getTopfoodApplicationContext(), "Usuario " + authRquest.getUsername() + " cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                    Log.d("CADASTRO SUCESSO", " ..............................................");
                    loginInteractor = new LoginInteractorImpl();
                    loginInteractor.login(authRquest.getUsername(), authRquest.getPassword(), null);
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("Sem conexão com a API.")
//                        .setPositiveButton("Ok", null);
//                builder.create().show();
                Log.d("Deu RUIMM", "Ruim demais");
            }
        });
    }

}
