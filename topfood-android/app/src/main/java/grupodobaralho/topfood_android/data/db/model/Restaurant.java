package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class Restaurant {

    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("products")
    private List<Product> products = new ArrayList<>();
    @SerializedName("createdAt")
    private String createAt;
    @SerializedName("updateAt")
    private String updateAt;
    @SerializedName("__v")
    private String whatIsThis;

    public Restaurant(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(String id, String name, List<Product> products, String createAt, String updateAt, String whatIsThis) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.whatIsThis = whatIsThis;
    }


}
