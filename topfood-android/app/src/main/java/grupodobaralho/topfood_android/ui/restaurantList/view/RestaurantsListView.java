package grupodobaralho.topfood_android.ui.restaurantList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.uiModels.productList.ProductListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.RestaurantsListInteractor;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.login.view.LoginActivity;
import grupodobaralho.topfood_android.ui.restaurantList.presenter.IRestaurantsListPresenter;
import grupodobaralho.topfood_android.ui.restaurantList.presenter.RestaurantsListPresenter;

public class RestaurantsListView extends AppCompatActivity implements IRestaurantsListView, SearchView.OnQueryTextListener{

    // TODO: remover parte logica da da activity > isso inclui o UserBusiness
    final UserBusiness userBusiness = UserBusiness.getInstance();

    private RestaurantsListAdapter adapter;
    private static IRestaurantsListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = (SearchView) findViewById(R.id.activity_catalog_search);
        searchView.setOnQueryTextListener(this);

//        if(presenter == null){
//            presenter = new RestaurantsListPresenter();
//        }
//        presenter.setView(this);
//        presenter.listAllRestaurants();

        //Botao para teste
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ProductListInteractor interactor = new ProductListInteractor();
                interactor.listProductsRestaurant("5b26b3e379625c0014a249b3");
                Toast.makeText(RestaurantsListView.this, "Funciona", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView rvRestaurants = (RecyclerView) findViewById(R.id.rv_restaurants);
        rvRestaurants.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rvRestaurants.setLayoutManager(layoutManager);

        //adapter = new RestaurantsListAdapter(this, presenter.getRestaurants());
        //rvRestaurants.setAdapter(adapter);
    }

    @Override
    public void showProgressBar(int visibilidade) {
        findViewById(R.id.pb_restaurants).setVisibility(visibilidade);
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToRestaurantDetail() {
//        startActivity(new Intent(this, .class));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
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
                startActivity(new Intent(this, RestaurantsListView.class));
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
