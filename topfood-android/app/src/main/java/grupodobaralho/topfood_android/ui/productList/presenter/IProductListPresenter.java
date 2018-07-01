package grupodobaralho.topfood_android.ui.productList.presenter;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.ui.productList.view.IProductListView;


public interface IProductListPresenter {

    interface OnProductListFinishedListener {
        void onApiError();
        void onSuccess();
    }

    void setView(IProductListView view);

    void listAllProducts(String restaurantId);

    List<Product> getProducts();

    List<Product> searchAProduct(String name);

    boolean hasUserLogged();

    void makeLogout();

}
