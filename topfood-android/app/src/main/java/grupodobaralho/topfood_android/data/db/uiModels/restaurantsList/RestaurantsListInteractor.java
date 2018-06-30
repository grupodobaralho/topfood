package grupodobaralho.topfood_android.data.db.uiModels.restaurantsList;

import android.util.Log;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.ui.restaurantList.presenter.IRestaurantsListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsListInteractor implements IRestaurantsListInteractor {

    private List<Restaurant> restaurants;

    @Override
    public void listAllRestaurants(final IRestaurantsListPresenter.OnRestaurantListFinishedListener listener) {
        //A retrofit instance that uses the API_EndPoint Interface
        Call<List<Restaurant>> call = RetrofitInstance.retrofitCreate().getRestaurants();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.code() != 200) {
                    listener.onApiError();
                    return;
                }
                restaurants = response.body();

                listener.onSuccess();
                return;
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e("Restaurants List", t.getLocalizedMessage(), t);
            }
        });
    }

    @Override
    public void chooseRestaurant(String idRestaurant) {

    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
