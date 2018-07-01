package grupodobaralho.topfood_android.ui.restaurantList.view;

import grupodobaralho.topfood_android.data.db.model.Restaurant;

public interface IRestaurantsListView {
    void showRestaurants();

    void goToRestaurantDetail(Restaurant restaurant);

    void showProgressBar();

    void hideProgressBar();

    void showToast(String mensagem);
}