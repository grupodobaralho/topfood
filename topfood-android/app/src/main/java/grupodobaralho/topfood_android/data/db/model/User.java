package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    private String id;
    @SerializedName("username")
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
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

}
