package grupodobaralho.topfood_android.ui.signUp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.prefs.TopfoodApplication;
import grupodobaralho.topfood_android.ui.login.view.LoginView;
import grupodobaralho.topfood_android.ui.restaurantList.view.RestaurantsListView;
import grupodobaralho.topfood_android.ui.signUp.presenter.SignUpPresenter;
import grupodobaralho.topfood_android.ui.signUp.presenter.ISignUpPresenter;

public class SignUpView extends AppCompatActivity implements ISignUpView, View.OnClickListener {

    private ProgressDialog mProgress;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private ISignUpPresenter presenter;
    private Intent intent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentFromList = getIntent();
        if (intentFromList != null)
            intent = intentFromList.getParcelableExtra(LoginView.EXTRA_INTENT);

        username = findViewById(R.id.input_cadastro_email);
        password = findViewById(R.id.input_cadastro_password);
        confirmPassword = findViewById(R.id.input_cadastro_confirm_password);
        findViewById(R.id.btn_signup_complete).setOnClickListener(this);
        findViewById(R.id.btn_signup_to_login).setOnClickListener(this);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processando...");
        mProgress.setMessage("Por favor, espere...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        presenter = new SignUpPresenter(this);
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
    public void onSuccessSignUp(String mensagem) {
        Toast.makeText(TopfoodApplication.getTopfoodApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        onBackPressed();
        finish();

    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.btn_signup_complete:
                presenter.validateSignUp(username.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
                break;
            case R.id.btn_signup_to_login:
                if(intent != null)
                    startActivity(new Intent(this, LoginView.class).putExtra(LoginView.EXTRA_INTENT, intent));
                else
                    startActivity(new Intent(this, LoginView.class));
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if(itemId == android.R.id.home) {
            this.onBackPressed();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
