package grupodobaralho.topfood_android.data.db.uiModels.restaurantsList;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsListInteractor implements IRestaurantsListInteractor {

    List<Restaurant> restaurants;

    @Override
    public List<Restaurant> getRestaurants() {
        //A retrofit instance that uses the API_EndPoint Interface
        Call<List<Restaurant>> call = RetrofitInstance.retrofitCreate().getRestaurants();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.body() != null) {
                    restaurants = response.body();
                    Log.d("My error", restaurants.toString());
                } else {
                    try {
                        Log.e("Restaurants List", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e("Restaurants List", t.getLocalizedMessage(), t);
            }
        });
        return restaurants;
    }
}