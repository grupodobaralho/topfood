package grupodobaralho.topfood_android.ui.restaurantList.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Restaurant;

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.ViewHolder> {

    private IRestaurantsListView restaurantsView;
    private List<Restaurant> restaurants;

    public RestaurantsListAdapter(RestaurantsListView restaurantsView, List<Restaurant> restaurants){
        this.restaurantsView = restaurantsView;
        this.restaurants = restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setDados(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName;
        private RelativeLayout relativeLayout;

        private ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.restaurant_name_tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout_restaurants);
            relativeLayout.setOnClickListener(this);
        }

        private void setDados(Restaurant restaurant){
            tvName.setText(restaurant.getName());
        }

        @Override
        public void onClick(View view) {
            restaurantsView.goToRestaurantDetail();
        }
    }
}