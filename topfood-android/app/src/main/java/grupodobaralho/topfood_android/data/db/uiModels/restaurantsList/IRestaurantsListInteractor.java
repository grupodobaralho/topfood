package grupodobaralho.topfood_android.data.db.uiModels.restaurantsList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;

public interface IRestaurantsListInteractor {

    interface OnListRestaurantsFinishedListener {
        void onEmailError();
        void onPasswordError();
        void onInvalidUsernameOrPassword();
        void onApiError();
        void onSuccess();
    }

    void listAllRestaurants();
    void chooseRestaurant(String idRestaurant);
    List<Restaurant> getRestaurants();

}
