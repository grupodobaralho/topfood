package grupodobaralho.topfood_android.ui.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.ui.login.presenter.ILoginPresenter;
import grupodobaralho.topfood_android.ui.login.presenter.LoginPresenter;
import grupodobaralho.topfood_android.ui.restaurantList.view.RestaurantsListView;
import grupodobaralho.topfood_android.ui.signUp.view.SignUpView;

/**
 * A login screen that offers login via email/password.
 * Classe feita com base em: https://github.com/antoniolg/androidmvp/tree/master/app/src/main/java/com/antonioleiva/mvpexample/app/Login
 */
public class LoginView extends AppCompatActivity implements ILoginView, View.OnClickListener {

    public static final String EXTRA_INTENT = "intent";

    private ProgressDialog mProgress;
    private EditText email;
    private EditText password;
    private ILoginPresenter presenter;
    private Intent intent;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentFromList = getIntent();
        if (intentFromList != null)
            intent = intentFromList.getParcelableExtra(EXTRA_INTENT);

        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processando...");
        mProgress.setMessage("Por favor, espere...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        presenter = new LoginPresenter(this);
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
    public void setResponseError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Usuário não encontrado ou senha incorreta")
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
        if (intent != null)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        else
            startActivity(new Intent(this, RestaurantsListView.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override public void onClick(View v) {
        Button btn = (Button) v;
        switch (btn.getId()) {
            case R.id.btn_login:
                presenter.validateCredentials(email.getText().toString(), password.getText().toString());
                break;
            case R.id.btn_signup:
                if(intent != null)
                    startActivity(new Intent(this, SignUpView.class).putExtra(EXTRA_INTENT, intent));
                else
                    startActivity(new Intent(this, SignUpView.class));
                break;
            default:
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

