package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;
import java.text.*;
import java.util.Date;

//https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit/23071080
public class Comment {

    @SerializedName("_id")
    private String id;
    @SerializedName("author")
    private User author;
    @SerializedName("text")
    private String text;
    @SerializedName("image")
    private String image;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;

    public Comment(String id, User author, String text, String image, String createdAt, String updatedAt) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        String[] parts = createdAt.split("T");
        String[] newDate = parts[0].split("-");
        return newDate[2] + "/" + newDate[1] + "/" + newDate[0];
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
