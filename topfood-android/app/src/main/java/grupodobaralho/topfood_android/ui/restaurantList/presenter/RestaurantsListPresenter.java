package grupodobaralho.topfood_android.ui.restaurantList.presenter;

import android.content.Context;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.IRestaurantsListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.RestaurantsListInteractor;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.restaurantList.view.IRestaurantsListView;

public class RestaurantsListPresenter implements IRestaurantsListPresenter, IRestaurantsListPresenter.OnRestaurantListFinishedListener {

    private UserBusiness userBusiness = UserBusiness.getInstance();
    private List<Restaurant> restaurants;
    private IRestaurantsListInteractor interactor;
    private IRestaurantsListView view;

    public RestaurantsListPresenter() {
        interactor = new RestaurantsListInteractor();
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
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        view.hideProgressBar();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    @Override
    public void onApiError() {
        hideProgressBar();
        view.showToast("Ocorreu algum erro no banco de dados.");
    }

    @Override
    public void onSuccess() {
        hideProgressBar();
        restaurants = interactor.getRestaurants();
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
}
