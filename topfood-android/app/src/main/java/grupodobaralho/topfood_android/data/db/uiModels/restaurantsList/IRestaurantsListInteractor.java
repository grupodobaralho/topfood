package grupodobaralho.topfood_android.data.db.uiModels.restaurantsList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.restaurantList.presenter.IRestaurantsListPresenter;

public interface IRestaurantsListInteractor {

    void listAllRestaurants(final IRestaurantsListPresenter.OnRestaurantListFinishedListener listener);

    List<Restaurant> getRestaurants();

}