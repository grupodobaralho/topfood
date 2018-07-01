package grupodobaralho.topfood_android.ui.productList.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private ProductListView productListView;
    private List<Product> products;

    ProductListAdapter(ProductListView productListView, List<Product> products){
        this.productListView = productListView;
        this.products = products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        holder.setDados(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName, tvPrice;
        private RelativeLayout relativeLayout;
        private Product myProduct;

        private ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.product_name_tv);
            tvPrice = itemView.findViewById(R.id.product_price_tv);
            relativeLayout = itemView.findViewById(R.id.relative_layout_product);
            relativeLayout.setOnClickListener(this);
        }

        private void setDados(Product product){
            tvName.setText(product.getName());
            tvPrice.setText(String.valueOf(product.getPrice()));
            myProduct = product;
        }

        @Override
        public void onClick(View view) {
            productListView.goToProductComments(myProduct);
        }
    }
}
