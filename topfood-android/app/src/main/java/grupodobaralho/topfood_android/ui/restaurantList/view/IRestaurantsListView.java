package grupodobaralho.topfood_android.ui.restaurantList.view;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;

public interface IRestaurantsListView {
    void showRestaurants(List<Restaurant> restaurants);

    void goToRestaurantDetail();

    void showProgressBar();

    void hideProgressBar();

    void showToast(String mensagem);
}
