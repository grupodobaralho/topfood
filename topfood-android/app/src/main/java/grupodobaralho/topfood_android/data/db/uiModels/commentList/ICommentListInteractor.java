package grupodobaralho.topfood_android.data.db.uiModels.commentList;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;

public interface ICommentListInteractor {

    List<Comment> listCommentsProduct(String restaurantId, String productId);

    Comment createComment(String restaurantId, String productId, String accessToken, String text);

    Comment updateComment(String restaurantId, String productId, String commentId, String accessToken, String text);

    boolean deleteComment(String restaurantId, String productId, String commentId, String accessToken);

}
