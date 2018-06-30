package grupodobaralho.topfood_android.ui.restaurantList.view;

public interface IRestaurantsListView {
    void showRestaurants();

    void goToRestaurantDetail();

    void showProgressBar();

    void hideProgressBar();

    void showToast(String mensagem);
}
