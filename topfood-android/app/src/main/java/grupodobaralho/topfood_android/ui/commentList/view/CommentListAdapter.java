package grupodobaralho.topfood_android.ui.commentList.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Comment;
import grupodobaralho.topfood_android.ui.commentList.presenter.ICommentListPresenter;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private CommentListView viewActivity;
    private ICommentListPresenter presenter;
    private List<Comment> comments;

    CommentListAdapter(CommentListView viewActivity, ICommentListPresenter presenter, List<Comment> comments) {
        this.viewActivity = viewActivity;
        this.presenter = presenter;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.ViewHolder holder, int position) {
        holder.setDados(comments.get(position), position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthor, tvDate, tvComment;
        private ImageView imgComment;
        private ImageButton btnDel;

        private ViewHolder(View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.comment_author_name_tv);
            tvDate = itemView.findViewById(R.id.comment_date_tv);
            tvComment = itemView.findViewById(R.id.comment_tv);
            imgComment = itemView.findViewById(R.id.comment_img);
            btnDel = itemView.findViewById(R.id.comment_delete_btn);

        }

        private void setDados(final Comment comment, final int position) {
            tvAuthor.setText(comment.getAuthor().getName());
            tvDate.setText(String.valueOf(comment.getCreatedAt()));
            tvComment.setText(comment.getText());

            if(presenter.isUserComment(comment)) {
                btnDel.setVisibility(View.VISIBLE);
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(viewActivity).setTitle("Deletando comentario")
                                .setMessage("Tem certeza que deseja deletar esse comentario?")
                                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        presenter.delComment(comment);
                                        comments.remove(position);
                                    }
                                })
                                .setNegativeButton("Não", null)
                                .show();
                    }


                });
            }
//            imgComment.setImageBitmap();
//            myProduct = comment
//            imgComment.setOnClickListener(this);
        }
    }
}
