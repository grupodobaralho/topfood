package grupodobaralho.topfood_android.ui.comment.presenter;

import grupodobaralho.topfood_android.data.db.uiModels.commentList.CommentListInteractor;
import grupodobaralho.topfood_android.data.db.uiModels.commentList.ICommentListInteractor;
import grupodobaralho.topfood_android.data.prefs.UserBusiness;
import grupodobaralho.topfood_android.ui.comment.view.ICommentView;

public class CommentPresenter implements ICommentPresenter, ICommentPresenter.OnCommentFinishedListener {

    private ICommentView view;
    private ICommentListInteractor interactor;
    private UserBusiness userBusiness = UserBusiness.getInstance();

    public CommentPresenter(ICommentView view) {
        this.view = view;
        this.interactor = new CommentListInteractor();
    }

    @Override
    public void postComment(String restaurantId, String productId, String text, String image) {
        if (view != null)
            view.showProgress();

        interactor.createComment(restaurantId, productId, userBusiness.getAccessToken(), text, this);
    }

    @Override
    public void onEditTextError() {
        if (view != null) {
            view.hideProgress();
            view.setEditTextError();
        }
    }

    @Override
    public void onApiError() {
        if (view != null) {
            view.hideProgress();
            view.showToast("Ocorreu um erro no banco de dados. Não foi possível postar o comentário.");
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.backToCommentList();
        }
    }


}
