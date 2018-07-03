package grupodobaralho.topfood_android.ui.productList.presenter;

import java.util.ArrayList;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.uiModels.productList.IProductListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.productList.ProductListInteractor;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.productList.view.IProductListView;

public class ProductListPresenter implements IProductListPresenter, IProductListPresenter.OnProductListFinishedListener {

    private IProductListInteractor interactor;
    private IProductListView view;
    private List<Product> searchProducts;
    private UserBusiness userBusiness = UserBusiness.getInstance();

    public ProductListPresenter() {
        interactor = new ProductListInteractor();
        searchProducts = new ArrayList<>();
    }

    @Override
    public void setView(IProductListView view) {
        this.view = view;
    }

    @Override
    public void listAllProducts(String restaurantId) {
        interactor.listProductsRestaurant(restaurantId, this);
        showProgressBar();
    }

    @Override
    public List<Product> getProducts() {
        return interactor.getProducts();
    }

    @Override
    public List<Product> searchAProduct(String name) {
        name = name.toLowerCase();
        searchProducts.clear();
        for(Product product: interactor.getProducts()) {
            if (product.getName().toLowerCase().contains(name))
                searchProducts.add(product);
        }
        return searchProducts;
    }

    @Override
    public void onApiError() {
        hideProgressBar();
        view.showToast("Ocorreu algum erro no banco de dados.");
    }

    @Override
    public void onSuccess() {
        hideProgressBar();
        view.showProducts();
    }

    @Override
    public boolean isUserLogged() {
        return userBusiness.isLogged();
    }

    @Override
    public void makeLogout() {
        userBusiness.removeAccessToken();
        view.showToast("Logout realizado com sucesso.");
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        view.hideProgressBar();
    }
}
