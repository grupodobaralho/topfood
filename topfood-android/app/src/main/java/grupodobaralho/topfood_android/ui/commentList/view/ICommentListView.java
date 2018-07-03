package grupodobaralho.topfood_android.ui.commentList.view;

import grupodobaralho.topfood_android.data.db.model.Comment;

public interface ICommentListView {

    void showComments();

    void updateList();

    void showProgressBar();

    void hideProgressBar();

    void showToast(String mensagem);

}
