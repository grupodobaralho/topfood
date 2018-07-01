package grupodobaralho.topfood_android.data.db.uiModels.productList;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.ui.productList.presenter.IProductListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListInteractor implements IProductListInteractor {

    private List<Product> products;

    @Override
    public void listProductsRestaurant(final String restaurantId, final IProductListPresenter.OnProductListFinishedListener listener) {
        //A retrofit instance that uses the API_EndPoint Interface
        Call<List<Product>> call = RetrofitInstance.retrofitCreate().getProductsRestaurant(restaurantId);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.code() != 200) {
                    listener.onApiError();
                    return;
                } else {
                    products = response.body();
                    listener.onSuccess();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
               listener.onApiError();
               return;
            }
        });
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
