package grupodobaralho.topfood_android.ui.productList.view;

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
import android.widget.TextView;
import android.widget.Toast;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.login.view.LoginView;
import grupodobaralho.topfood_android.ui.productList.presenter.IProductListPresenter;
import grupodobaralho.topfood_android.ui.productList.presenter.ProductListPresenter;

public class ProductListView extends AppCompatActivity implements IProductListView, SearchView.OnQueryTextListener {

    public static final String EXTRA_RESTAURANT = "restaurant";
    private Restaurant restaurant;
    private IProductListPresenter presenter;
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentFromList = getIntent();
        if (intentFromList != null)
            restaurant = (Restaurant) intentFromList.getSerializableExtra(EXTRA_RESTAURANT);

        SearchView searchView = findViewById(R.id.product_list_search);
        searchView.setOnQueryTextListener(this);

        TextView tvTitleRestaturant = findViewById(R.id.restaurant_title_in_product_list_tv);
        TextView tvRatingRestaturant = findViewById(R.id.restaurant_rating_in_product_list_tv);

        tvTitleRestaturant.setText(restaurant.getName());
        tvRatingRestaturant.setText(restaurant.getRating());

        if(presenter == null)
            presenter = new ProductListPresenter();
        presenter.setView(this);
        presenter.listAllProducts(restaurant.getId());
    }

    @Override
    public void showProducts() {
        //Variaveis necessarias para trabalhar com recyclerview
        RecyclerView rvProducts = findViewById(R.id.rv_products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setVisibility(View.VISIBLE);

        adapter = new ProductListAdapter(this, presenter.getProducts());
        rvProducts.setAdapter(adapter);
    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.pb_products).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        findViewById(R.id.pb_products).setVisibility(View.GONE);
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToProductComments(Product product) {
//        Intent intent = new Intent(this, ProductListView.class);
//        intent.putExtra(ProductListView.EXTRA_RESTAURANT, restaurant);
//        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return onQueryTextChange(s);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(adapter != null) {
            adapter.setProducts(presenter.searchAProduct(s));
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

        switch (itemId) {

            case R.id.action_logout:
                if (!presenter.hasUserLogged()) {
                    startActivity(new Intent(this, LoginView.class));
                } else {
                    presenter.makeLogout();
                    startActivity(new Intent(this, ProductListView.class));
                    finish();
                }
                break;

            case android.R.id.home:
                this.onBackPressed();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
