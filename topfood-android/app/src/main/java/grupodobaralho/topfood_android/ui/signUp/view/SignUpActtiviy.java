package grupodobaralho.topfood_android.ui.signUp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.ui.restaurantList.view.RestaurantsListView;
import grupodobaralho.topfood_android.ui.signUp.presenter.SignUpPresenter;
import grupodobaralho.topfood_android.ui.signUp.presenter.ISignUpPresenter;

public class SignUpActtiviy extends AppCompatActivity implements ISignUpView, View.OnClickListener {

    private ProgressDialog mProgress;
    private EditText nomeCompleto;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private ISignUpPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_cadastro);

        nomeCompleto = (EditText) findViewById(R.id.input_cadastro_name);
        username = (EditText) findViewById(R.id.input_cadastro_email);
        password = (EditText) findViewById(R.id.input_cadastro_password);
        confirmPassword = (EditText) findViewById(R.id.input_cadastro_confirm_password);
        findViewById(R.id.btn_cadastro_signup).setOnClickListener(this);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processando...");
        mProgress.setMessage("Por favor, espere...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        presenter = new SignUpPresenter(this);
    }


    @Override
    public void setNomeCompletoError() {
        nomeCompleto.setError(getString(R.string.nome_completo_error));
    }

    @Override
    public void verifyPasswordError() {
        confirmPassword.setError(getString(R.string.verify_password_error));
    }

    @Override
    public void showProgress() {
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.email_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void setResponseError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Username já registrado ou senha inválida.")
                .setPositiveButton("Ok", null);
        builder.create().show();
    }

    @Override
    public void setApiError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sem conexão com a API.")
                .setPositiveButton("Ok", null);
        builder.create().show();
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, RestaurantsListView.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        presenter.validateSignUp(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
    }
}