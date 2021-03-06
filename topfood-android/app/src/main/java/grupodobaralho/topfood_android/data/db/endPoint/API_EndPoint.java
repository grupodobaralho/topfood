package grupodobaralho.topfood_android.data.db.endPoint;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.AuthRequest;
import grupodobaralho.topfood_android.data.db.model.AuthResponse;
import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.data.db.model.CommentRequest;
import grupodobaralho.topfood_android.data.db.model.SignUpResponse;
import grupodobaralho.topfood_android.data.db.model.Text;
import grupodobaralho.topfood_android.data.db.model.User;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.data.db.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//https://medium.com/cr8resume/make-your-hand-dirty-with-retrofit-2-a-type-safe-http-client-for-android-and-java-c546f88b3a51
public interface API_EndPoint {

    @Headers({"Content-Type: application/json"})
    @GET("users/profile")
    Call<User> getProfile(@Header("Authorization") String accessToken);

    @Headers({"Content-Type: application/json"})
    @POST("users/login")
    Call<AuthResponse> authUser(@Body AuthRequest request);

    @Headers({"Content-Type: application/json"})
    @POST("users/signup")
    Call<SignUpResponse> signUphUser(@Body AuthRequest request);

    @Headers({"Content-Type: application/json"})
    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();

    @Headers({"Content-Type: application/json"})
    @GET("restaurants/{restaurantId}/products")
    Call<List<Product>> getProductsRestaurant(@Path("restaurantId") String restaurantId);

    @Headers({"Content-Type: application/json"})
    @GET("restaurants/{restaurantId}/products/{productId}/comments")
    Call<List<Comment>> getCommentsProduct(@Path("restaurantId") String restaurantId, @Path("productId") String productId);

    @Headers({"Content-Type: application/json"})
    @POST("restaurants/{restaurantId}/products/{productId}/comments")
    Call<Comment> createComment(@Path("restaurantId") String restaurantId, @Path("productId") String productId,
                                @Header("Authorization") String accessToken,
                                @Body CommentRequest commentRequest);

    @Headers({"Content-Type: application/json"})
    @PUT("restaurants/{restaurantId}/products/{productId}/comments/{commentId}")
    Call<Comment> updateComment(@Path("restaurantId") String restaurantId, @Path("productId") String productId, @Path("commentId") String commentId,
                                @Header("Authorization") String accessToken,
                                @Body Text text);


    @Headers({"Content-Type: application/json"})
    @DELETE("restaurants/{restaurantId}/products/{productId}/comments/{commentId}")
    Call<Comment> deleteComment(@Path("restaurantId") String restaurantId, @Path("productId") String productId, @Path("commentId") String commentId,
                                @Header("Authorization") String accessToken);
}
