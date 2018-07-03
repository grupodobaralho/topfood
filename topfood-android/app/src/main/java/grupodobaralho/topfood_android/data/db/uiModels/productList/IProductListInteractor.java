package grupodobaralho.topfood_android.data.db.uiModels.productList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.ui.productList.presenter.IProductListPresenter;

public interface IProductListInteractor {

    void listProductsRestaurant(final String restaurantId, final IProductListPresenter.OnProductListFinishedListener listener);

    List<Product> getProducts();

}
