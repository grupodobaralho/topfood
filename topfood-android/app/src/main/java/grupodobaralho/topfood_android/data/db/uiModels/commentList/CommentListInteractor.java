package grupodobaralho.topfood_android.data.db.uiModels.commentList;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.data.db.model.CommentRequest;
import grupodobaralho.topfood_android.data.db.model.Text;
import grupodobaralho.topfood_android.data.network.RetrofitInstance;
import grupodobaralho.topfood_android.ui.comment.presenter.ICommentPresenter;
import grupodobaralho.topfood_android.ui.commentList.presenter.ICommentListPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentListInteractor implements ICommentListInteractor {

    private List<Comment> comments;
    private Comment comment;

    @Override
    public void listCommentsProduct(final String restaurantId, final String productId, final ICommentListPresenter.OnCommentListFinishedListener listener) {
        Call<List<Comment>> call = RetrofitInstance.retrofitCreate().getCommentsProduct(restaurantId, productId);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.code() != 200) {
                    listener.onApiError();
                    return;
                }

                comments = response.body();
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                listener.onApiError();
            }
        });
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void createComment(String restaurantId, String productId, String accessToken, String text, String img, final ICommentPresenter.OnCommentFinishedListener listener) {

        if(text == null || text.isEmpty()) {
            listener.onEditTextError();
            return;
        }

        CommentRequest commentRequest = new CommentRequest(text, img);
        Call<Comment> call = RetrofitInstance.retrofitCreate().createComment(restaurantId, productId,  accessToken, commentRequest);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.code() != 200) {
                    listener.onApiError();
                    return;
                }
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                listener.onApiError();
            }
        });
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
    public void deleteComment(String restaurantId, String productId, String commentId, String accessToken, final ICommentListPresenter.OnCommentListFinishedListener listener) {

        Call<Comment> call = RetrofitInstance.retrofitCreate().deleteComment(restaurantId, productId, commentId, accessToken);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.code() != 200) {
                    listener.onDelError();
                    return;
                }
                listener.onSuccessDel();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                listener.onDelError();
            }
        });
    }
}
