package grupodobaralho.topfood_android.ui.comment.view;

public interface ICommentView {

    void backToCommentList();

    void showToast(String mensagem);

    void showProgress();

    void hideProgress();

    void setEditTextError();
}
