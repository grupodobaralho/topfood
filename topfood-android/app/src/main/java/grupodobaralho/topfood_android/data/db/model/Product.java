package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

class Product {

    @SerializedName("id")
    private String id;
    @SerializedName("restaurant")
    private Restaurant restaurant;
    @SerializedName("price")
    private String price;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;

    //It is necessary to validate the data types of the attributes. Ex: price might be a double
    public Product(String id, Restaurant restaurant, String price, String name, String type) {
        this.id = id;
        this.restaurant = restaurant;
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
