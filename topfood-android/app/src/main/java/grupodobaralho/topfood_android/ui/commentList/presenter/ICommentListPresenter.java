package grupodobaralho.topfood_android.ui.commentList.presenter;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.ui.commentList.view.ICommentListView;

public interface ICommentListPresenter {

    interface OnCommentListFinishedListener {
        void onApiError();
        void onSuccess();
        void onSuccessDel();
    }

    void setView(ICommentListView view);

    void setIds(String restaurantId, String productId);

    void listAllComments();

    void delComment(Comment comment);

    List<Comment> getComments();

    boolean hasUserLogged();

    void makeLogout();

    void showProgressBar();

    void hideProgressBar();

    boolean wasTheUserLoggedWhoCommented(Comment comment);

}
