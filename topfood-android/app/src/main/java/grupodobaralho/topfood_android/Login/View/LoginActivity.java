package grupodobaralho.topfood_android.Login.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import grupodobaralho.topfood_android.Cadastro.View.CadastroActtiviy;
import grupodobaralho.topfood_android.Login.Model.LoginInteractorImpl;
import grupodobaralho.topfood_android.Login.Presenter.LoginPresenter;
import grupodobaralho.topfood_android.Login.Presenter.LoginPresenterImpl;
import grupodobaralho.topfood_android.MainActivity;
import grupodobaralho.topfood_android.R;

/**
 * A login screen that offers login via email/password.
 * Classe feita com base em: https://github.com/antoniolg/androidmvp/tree/master/app/src/main/java/com/antonioleiva/mvpexample/app/Login
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText email;
    private EditText password;
    private LoginPresenter presenter;

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
                break;
            case R.id.btn_signup:
                startActivity(new Intent(this, CadastroActtiviy.class));
                finish();
                break;
            default:
                return;
        }
    }

}

