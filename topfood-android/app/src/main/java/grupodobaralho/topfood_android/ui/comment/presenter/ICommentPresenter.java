package grupodobaralho.topfood_android.ui.comment.presenter;

public interface ICommentPresenter {

    interface OnCommentFinishedListener {
        void onEditTextError();
        void onApiError();
        void onSuccess();
    }

    void postComment(String restaurantId, String productId, String text, String image);
}
