package grupodobaralho.topfood_android.data.network;

import grupodobaralho.topfood_android.data.db.endPoint.UserEP;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://topfood.herokuapp.com/";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static UserEP retrofitCreate() {
        return retrofit.create(UserEP.class);
    }

}
