package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("token")
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
}

