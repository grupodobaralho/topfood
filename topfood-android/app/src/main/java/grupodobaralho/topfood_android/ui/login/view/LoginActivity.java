package grupodobaralho.topfood_android.ui.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import grupodobaralho.topfood_android.MainActivity;
import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.ui.login.presenter.LoginPresenter;
import grupodobaralho.topfood_android.ui.login.presenter.LoginPresenterImpl;
import grupodobaralho.topfood_android.ui.signUp.view.CadastroActtiviy;

/**
 * A login screen that offers login via email/password.
 * Classe feita com base em: https://github.com/antoniolg/androidmvp/tree/master/app/src/main/java/com/antonioleiva/mvpexample/app/Login
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressDialog mProgress;
    private EditText email;
    private EditText password;
    private LoginPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processando...");
        mProgress.setMessage("Por favor, espere...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        presenter = new LoginPresenterImpl(this);
    }

    @Override protected void onDestroy() {
//        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        mProgress.show();
    }

    @Override public void hideProgress() {
        mProgress.dismiss();
    }

    @Override public void setUsernameError() {
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

