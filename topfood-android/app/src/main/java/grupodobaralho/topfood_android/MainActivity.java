package grupodobaralho.topfood_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import grupodobaralho.topfood_android.data.localStorage.UserBusiness;
import grupodobaralho.topfood_android.ui.login.view.LoginActivity;

public class MainActivity extends AppCompatActivity {

    // TODO: remover parte logica da da activity > isso inclui o UserBusiness
    final UserBusiness userBusiness = UserBusiness.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);

        if(!userBusiness.isLogged()) {
            menu.findItem(R.id.action_logout).setTitle("Fazer Login");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == R.id.action_logout) {

            if(!userBusiness.isLogged()) {
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                userBusiness.removeAccessToken();
                Toast.makeText(this, "Logout realizado com sucesso.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
