package grupodobaralho.topfood_android.data.db.uiModels.productList;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListInteractor implements IProductListInteractor {

    List<Product> products;

    @Override
    public List<Product> listProductsRestaurant(String restaurantId) {
        //A retrofit instance that uses the API_EndPoint Interface
        Call<List<Product>> call = RetrofitInstance.retrofitCreate().getProductsRestaurant(restaurantId);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    products = response.body();
                    Log.d("My error", products.toString());
                } else {
                    try {
                        Log.e("Product List", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Product List", t.getLocalizedMessage(), t);
            }
        });
        return products;
    }
}
