package grupodobaralho.topfood_android.ui.Login.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import grupodobaralho.topfood_android.data.db.model.Teste;
import grupodobaralho.topfood_android.ui.Cadastro.View.CadastroActtiviy;
import grupodobaralho.topfood_android.ui.Login.Model.LoginInteractorImpl;
import grupodobaralho.topfood_android.ui.Login.Presenter.LoginPresenter;
import grupodobaralho.topfood_android.ui.Login.Presenter.LoginPresenterImpl;
import grupodobaralho.topfood_android.MainActivity;
import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.endPoint.UserEP;
import grupodobaralho.topfood_android.data.db.model.User;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 * Classe feita com base em: https://github.com/antoniolg/androidmvp/tree/master/app/src/main/java/com/antonioleiva/mvpexample/app/Login
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText email;
    private EditText password;
    private LoginPresenter presenter;

    private Teste teste;

    //A retrofit instance that uses the UserEP Interface
    UserEP service = RetrofitInstance.getRetrofitInstance().create(UserEP.class);

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress_login);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);


        // Utiliza singleton
        presenter = LoginPresenterImpl.getInstance(this,new LoginInteractorImpl());

    }

    @Override protected void onDestroy() {
//        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void setEmailError() {
        email.setError(getString(R.string.email_error));
    }

    @Override public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override public void onClick(View v) {
        Button btn = (Button) v;
        switch (btn.getId()) {
            case R.id.btn_login:
                presenter.validateCredentials(email.getText().toString(), password.getText().toString());


                //esse treixo ta bugando, mas deveria funcionar
                //Call<Teste> call = service.getTesteData();
                ///**Log the URL called*/
                // Log.wtf("URL Called", call.request().url() + "");

                Call<Teste> call = service.getTesteData();
                call.enqueue(new Callback<Teste>() {
                    @Override
                    public void onResponse(Call<Teste> call, Response<Teste> response) {
                        if (response.body() != null) {
                            teste = response.body();
                        }else {
                            try {
                                Log.e("ERRO", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Teste> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_signup:
                startActivity(new Intent(this, CadastroActtiviy.class));
                finish();
                break;
            default:
                return;
        }
    }
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(teste.toString())
//                .setCancelable(false)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();

}

