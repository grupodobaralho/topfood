package grupodobaralho.topfood_android.ui.productList.view;

import grupodobaralho.topfood_android.data.db.model.Product;

public interface IProductListView {

    void showProducts();

    void goToProductComments(Product product);

    void showProgressBar();

    void hideProgressBar();

    void showToast(String mensagem);

}
