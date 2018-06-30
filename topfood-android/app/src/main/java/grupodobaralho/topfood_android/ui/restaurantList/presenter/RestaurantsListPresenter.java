package grupodobaralho.topfood_android.ui.restaurantList.presenter;

import android.content.Context;
import android.view.View;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.IRestaurantsListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.restaurantsList.RestaurantsListInteractor;
import grupodobaralho.topfood_android.ui.restaurantList.view.IRestaurantsListView;

public class RestaurantsListPresenter implements IRestaurantsListPresenter, IRestaurantsListPresenter.OnRestaurantListFinishedListener {

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
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar(visibilidade);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public void onApiError() {
        view.showToast("Ocorreu algum erro no banco de dados.");
    }

    @Override
    public void onSuccess() {
        restaurants = interactor.getRestaurants();
        view.showRestaurants();
    }
}
