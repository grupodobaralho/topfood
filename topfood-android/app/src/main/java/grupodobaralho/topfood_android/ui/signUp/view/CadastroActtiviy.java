package grupodobaralho.topfood_android.ui.signUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import grupodobaralho.topfood_android.MainActivity;
import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.ui.signUp.presenter.CadastroPresenter;
import grupodobaralho.topfood_android.ui.signUp.presenter.CadastroPresenterImpl;

public class CadastroActtiviy extends AppCompatActivity implements CadastroView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText nomeCompleto;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private CadastroPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_cadastro);

        progressBar = (ProgressBar) findViewById(R.id.progress_cadastro);
        nomeCompleto = (EditText) findViewById(R.id.input_cadastro_name);
        username = (EditText) findViewById(R.id.input_cadastro_email);
        password = (EditText) findViewById(R.id.input_cadastro_password);
        confirmPassword = (EditText) findViewById(R.id.input_cadastro_confirm_password);
        findViewById(R.id.btn_cadastro_signup).setOnClickListener(this);

        presenter = new CadastroPresenterImpl(this);

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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
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
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        presenter.validateSignUp(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
    }
}
