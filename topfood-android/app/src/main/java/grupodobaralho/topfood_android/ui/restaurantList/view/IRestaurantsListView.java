package grupodobaralho.topfood_android.ui.restaurantList.view;

public interface IRestaurantsListView {
    void goToRestaurantDetail();

    void showProgressBar(int visibilidade);

    void showToast(String mensagem);
}
