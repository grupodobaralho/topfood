package grupodobaralho.topfood_android.ui.restaurantList.presenter;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.restaurantList.view.IRestaurantsListView;

public interface IRestaurantsListPresenter {

    interface OnRestaurantListFinishedListener {
        void onApiError();
        void onSuccess();
    }

    void setView(IRestaurantsListView view);

    void listAllRestaurants();

    List<Restaurant> getRestaurants();

    List<Restaurant> searchARestaurant(String name);

    boolean isUserLogged();

    void makeLogout();

    void showProgressBar();

    void hideProgressBar();
}
