package grupodobaralho.topfood_android.ui.restaurantList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.login.view.LoginView;
import grupodobaralho.topfood_android.ui.productList.view.ProductListView;
import grupodobaralho.topfood_android.ui.restaurantList.presenter.IRestaurantsListPresenter;
import grupodobaralho.topfood_android.ui.restaurantList.presenter.RestaurantsListPresenter;

public class RestaurantsListView extends AppCompatActivity implements IRestaurantsListView, SearchView.OnQueryTextListener{

    private IRestaurantsListPresenter presenter;
    private RestaurantsListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        SearchView searchView = findViewById(R.id.restaurant_list_search);
        searchView.setOnQueryTextListener(this);

        if(presenter == null)
            presenter = new RestaurantsListPresenter();
        presenter.setView(this);
        presenter.listAllRestaurants();
    }

    @Override
    public void showRestaurants() {
        //Variaveis necessarias para trabalhar com recyclerview
        RecyclerView rvRestaurants = findViewById(R.id.rv_restaurants);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvRestaurants.setLayoutManager(layoutManager);
        rvRestaurants.setVisibility(View.VISIBLE);

        adapter = new RestaurantsListAdapter(this, presenter.getRestaurants());
        rvRestaurants.setAdapter(adapter);
    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.pb_restaurants).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        findViewById(R.id.pb_restaurants).setVisibility(View.GONE);
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToRestaurantDetail(Restaurant restaurant) {
        Intent intent = new Intent(this, ProductListView.class);
        intent.putExtra(ProductListView.EXTRA_RESTAURANT, restaurant);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return onQueryTextChange(s);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(adapter != null) {
            adapter.setRestaurants(presenter.searchARestaurant(s));
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);

        if(!presenter.hasUserLogged()) {
            menu.findItem(R.id.action_logout).setTitle("Fazer Login");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == R.id.action_logout) {

            if(!presenter.hasUserLogged()) {
                startActivity(new Intent(this, LoginView.class));
            } else {
                presenter.makeLogout();
                startActivity(new Intent(this, RestaurantsListView.class));
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}