package grupodobaralho.topfood_android.data.db.uiModels.userInteractor;

import android.util.Log;

import java.io.IOException;

import grupodobaralho.topfood_android.data.db.model.User;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInteractor implements IUserInteractor {

    private User user;

    @Override
    public void getProfile(String token) {
        Call<User> call = RetrofitInstance.retrofitCreate().getProfile(token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    user = response.body();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
