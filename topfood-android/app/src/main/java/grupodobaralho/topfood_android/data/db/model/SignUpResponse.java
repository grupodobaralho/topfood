package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("success")
    private Boolean success;

    public Boolean isSuccess() {
        return success;
    }


}
