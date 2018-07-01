package grupodobaralho.topfood_android.data.db.uiModels.commentList;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.data.db.model.Text;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentListInteractor implements ICommentListInteractor {

    private List<Comment> comments;
    private Comment comment;

    @Override
    public List<Comment> listCommentsProduct(String restaurantId, String productId) {
        Call<List<Comment>> call = RetrofitInstance.retrofitCreate().getCommentsProduct(restaurantId, productId);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.body() != null) {
                    comments = response.body();
                } else {
                    try {
                        Log.e("Comment List", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Comment List Failed", t.getLocalizedMessage(), t);
            }
        });
        return comments;
    }

    @Override
    public Comment createComment(String restaurantId, String productId, String accessToken, String text) {
        Text oText = new Text(text);
        Call<Comment> call = RetrofitInstance.retrofitCreate().createComment(restaurantId, productId,  accessToken, oText);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.body() != null) {
                    comment = response.body();
                } else {
                    try {
                        Log.e("New Comment", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e("New Comment Failed", t.getLocalizedMessage(), t);
            }
        });
        return comment;
    }

    @Override
    public Comment updateComment(String restaurantId, String productId, String commentId, String accessToken, String text) {
        Text oText = new Text(text);
        Call<Comment> call = RetrofitInstance.retrofitCreate().updateComment(restaurantId, productId, commentId
                , accessToken, oText);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.body() != null) {
                    comment = response.body();
                } else {
                    try {
                        Log.e("New Comment", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e("New Comment Failed", t.getLocalizedMessage(), t);
            }
        });
        return comment;
    }

    @Override
    public boolean deleteComment(String restaurantId, String productId, String commentId, String accessToken) {
        Call<Comment> call = RetrofitInstance.retrofitCreate().deleteComment(restaurantId, productId, commentId
                , accessToken);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.body() != null) {
                    comment = response.body();
                } else {
                    try {
                        Log.e("Deleting a Comment", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e("Comment not Deleted", t.getLocalizedMessage(), t);
            }
        });
        return false;
    }
}
