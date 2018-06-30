package grupodobaralho.topfood_android.data.db.uiModels.restaurantsList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;

public interface IRestaurantsListInteractor {

    List<Restaurant> listAllRestaurants();
    void chooseRestaurant(String idRestaurant);

}
