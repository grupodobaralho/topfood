package grupodobaralho.topfood_android.data.db.endPoint;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.AuthResponse;
import grupodobaralho.topfood_android.data.db.model.SignUpResponse;
import grupodobaralho.topfood_android.data.db.model.User;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.db.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

//https://medium.com/cr8resume/make-your-hand-dirty-with-retrofit-2-a-type-safe-http-client-for-android-and-java-c546f88b3a51
public interface API_EndPoint {
    @GET("?/?/")
    Call<User> getUserData();

    //Faz login
    @GET("users")
    Call<List<User>> getTesteData();

    @POST("users/login")
    Call<AuthResponse> authUser(@Body AuthRequest request);

    @POST("users/signup")
    Call<SignUpResponse> signUphUser(@Body AuthRequest request);

    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();

    @GET("restaurants/{restaurantId}/products")
    Call<List<Product>> getProductsRestaurant(@Path("restaurantId") String restaurantId);

    // Utilizamos o seguinte header para acessar dados do user logado:
//    (@Header("Authorization") String accessToken);

}
