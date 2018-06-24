package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

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

    public Restaurant(String id, String name, List<Product> products, String createAt, String updateAt, String whatIsThis) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.whatIsThis = whatIsThis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getWhatIsThis() {
        return whatIsThis;
    }

    public void setWhatIsThis(String whatIsThis) {
        this.whatIsThis = whatIsThis;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", whatIsThis='" + whatIsThis + '\'' +
                '}';
    }
}
