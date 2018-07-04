package grupodobaralho.topfood_android.data.db.uiModels.commentList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.ui.comment.presenter.ICommentPresenter;
import grupodobaralho.topfood_android.ui.commentList.presenter.ICommentListPresenter;

public interface ICommentListInteractor {

    void listCommentsProduct(final String restaurantId, final String productId, final ICommentListPresenter.OnCommentListFinishedListener listener);

    List<Comment> getComments();

    void createComment(String restaurantId, String productId, String accessToken, String text, String img, final ICommentPresenter.OnCommentFinishedListener listener);

    Comment updateComment(String restaurantId, String productId, String commentId, String accessToken, String text);

    void deleteComment(final String restaurantId, final String productId, final String commentId, final String accessToken, final ICommentListPresenter.OnCommentListFinishedListener listener);

}
