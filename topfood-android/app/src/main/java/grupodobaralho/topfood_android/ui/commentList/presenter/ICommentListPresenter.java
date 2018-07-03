package grupodobaralho.topfood_android.ui.commentList.presenter;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.ui.commentList.view.ICommentListView;

public interface ICommentListPresenter {

    interface OnCommentListFinishedListener {
        void onApiError();
        void onSuccess();
    }

    void setView(ICommentListView view);

    void listAllComments(String restaurantId, String productId);

    List<Comment> getComments();

    boolean hasUserLogged();

    void makeLogout();

    void showProgressBar();

    void hideProgressBar();

}
