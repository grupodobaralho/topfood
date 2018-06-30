package grupodobaralho.topfood_android.data.db.uiModels.productList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Product;

public interface IProductListInteractor {

    List<Product> listProductsRestaurant(String restaurantId);

}
