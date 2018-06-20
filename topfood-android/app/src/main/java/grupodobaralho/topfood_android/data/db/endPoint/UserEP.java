package grupodobaralho.topfood_android.data.db.endPoint;

import grupodobaralho.topfood_android.data.db.model.Teste;
import grupodobaralho.topfood_android.data.db.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
//https://medium.com/cr8resume/make-your-hand-dirty-with-retrofit-2-a-type-safe-http-client-for-android-and-java-c546f88b3a51
public interface UserEP {
    @GET("?/?/")
    Call<User> getUserData();

    @GET("")
    Call<Teste> getTesteData();
}
