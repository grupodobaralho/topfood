package grupodobaralho.topfood_android.data.network;

import grupodobaralho.topfood_android.BuildConfig;
import grupodobaralho.topfood_android.data.db.endPoint.API_EndPoint;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://topfood.herokuapp.com/";

    private static Retrofit getRetrofitInstance() {

        //Adding HTTP log to retrofit.  There are 4 levels: NONE, BASIC, HEADERS, BODY
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static API_EndPoint retrofitCreate() {
        return getRetrofitInstance().create(API_EndPoint.class);
    }

}
