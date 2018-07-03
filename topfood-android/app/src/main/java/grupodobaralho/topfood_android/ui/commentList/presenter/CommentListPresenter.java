package grupodobaralho.topfood_android.ui.commentList.presenter;

import java.util.List;

import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.data.db.uiModels.commentList.CommentListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.commentList.ICommentListInteractor;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.commentList.view.ICommentListView;


public class CommentListPresenter implements ICommentListPresenter, ICommentListPresenter.OnCommentListFinishedListener {

    private ICommentListInteractor interactor;
    private ICommentListView view;
    private UserBusiness userBusiness = UserBusiness.getInstance();
    private String restaurantId, productId;

    public CommentListPresenter() {
        interactor = new CommentListInteractor();
    }

    @Override
    public void setView(ICommentListView view) {
        this.view = view;
    }

    @Override
    public void setIds(String restaurantId, String productId) {
        this.restaurantId = restaurantId;
        this.productId = productId;
    }

    @Override
    public void listAllComments() {
        interactor.listCommentsProduct(restaurantId, productId, this);
        showProgressBar();
    }

    @Override
    public void delComment(Comment comment) {
        interactor.deleteComment(restaurantId, productId, comment.getId(), userBusiness.getAccessToken(), this);
    }

    @Override
    public List<Comment> getComments() {
        return interactor.getComments();
    }

    @Override
    public void onApiError() {
        hideProgressBar();
        view.showToast("Ocorreu algum erro no banco de dados.");
    }

    @Override
    public void onSuccess() {
        hideProgressBar();
        view.showComments();
    }

    @Override
    public void onSuccessDel() {
        view.showToast("Mensagem removida com sucesso.");
        interactor.listCommentsProduct(restaurantId, productId, this);
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        view.hideProgressBar();
    }

    @Override
    public boolean hasUserLogged() {
        return userBusiness.isLogged();
    }

    @Override
    public void makeLogout() {
        userBusiness.removeAccessToken();
        view.showToast("Logout realizado com sucesso.");
    }

    @Override
    public boolean wasTheUserLoggedWhoCommented(Comment comment) {
        if(hasUserLogged() && comment.getAuthor().getId().equals(userBusiness.getUserId()))
            return true;
        else
            return false;
    }
}
