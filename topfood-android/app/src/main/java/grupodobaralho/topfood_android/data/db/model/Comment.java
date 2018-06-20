package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    private String id;
    @SerializedName("user")
    private User user;
    @SerializedName("product")
    private Product product;
    @SerializedName("text")
    private String text;
    @SerializedName("date")
    private String date;
    @SerializedName("image")
    private String image;

    public Comment(String id, User user, Product product, String text, String date, String image) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.text = text;
        this.date = date;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
