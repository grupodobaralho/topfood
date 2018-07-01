package grupodobaralho.topfood_android.ui.restaurantList.presenter;

import java.util.ArrayList;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.IRestaurantsListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.RestaurantsListInteractor;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.restaurantList.view.IRestaurantsListView;

public class RestaurantsListPresenter implements IRestaurantsListPresenter, IRestaurantsListPresenter.OnRestaurantListFinishedListener {


    private IRestaurantsListInteractor interactor;
    private IRestaurantsListView view;
    private List<Restaurant> auxRestaurants;
    private UserBusiness userBusiness = UserBusiness.getInstance();

    public RestaurantsListPresenter() {
        interactor = new RestaurantsListInteractor();
        auxRestaurants = new ArrayList<>();
    }

    @Override
    public void setView(IRestaurantsListView view) {
        this.view = view;
    }

    @Override
    public void listAllRestaurants() {
        interactor.listAllRestaurants(this);
        showProgressBar();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return interactor.getRestaurants();
    }

    @Override
    public List<Restaurant> searchARestaurant(String name) {
        name = name.toLowerCase();
        auxRestaurants.clear();
        for(Restaurant restaurant: interactor.getRestaurants()) {
            if (restaurant.getName().toLowerCase().contains(name))
                auxRestaurants.add(restaurant);
        }
        return auxRestaurants;
    }

    @Override
    public void onApiError() {
        hideProgressBar();
        view.showToast("Ocorreu algum erro no banco de dados.");
    }

    @Override
    public void onSuccess() {
        hideProgressBar();
        view.showRestaurants();
    }

    @Override
    public boolean hasUserLogged() {
        return userBusiness.isLogged();
    }

    @Override
    public void makeLogout() {
        userBusiness.removeAccessToken();
        view.showToast("Logout realizado com sucesso.");
    }

    public void showProgressBar() {
        view.showProgressBar();
    }

    public void hideProgressBar() {
        view.hideProgressBar();
    }
}
