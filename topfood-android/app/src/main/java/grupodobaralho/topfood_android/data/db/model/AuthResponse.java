package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("token")
    private String access_token;

    @SerializedName("_id")
    private String id;

    public String getAccess_token() {
        return access_token;
    }

    public String getId() {
        return id;
    }
}

