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

    public CommentListPresenter() {
        interactor = new CommentListInteractor();
    }

    @Override
    public void setView(ICommentListView view) {
        this.view = view;
    }

    @Override
    public void listAllComments(String restaurantId, String productId) {
        interactor.listCommentsProduct(restaurantId, productId, this);
        showProgressBar();
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
    public boolean hasUserLogged() {
        return userBusiness.isLogged();
    }

    @Override
    public void makeLogout() {
        userBusiness.removeAccessToken();
        view.showToast("Logout realizado com sucesso.");
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        view.hideProgressBar();
    }
}
