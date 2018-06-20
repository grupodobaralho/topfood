package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

class Restaurant {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public Restaurant(String id, String name) {
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
