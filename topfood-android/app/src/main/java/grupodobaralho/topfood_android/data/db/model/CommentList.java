package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentList {

    @SerializedName("notice_list")
    private ArrayList<Comment> CommentList;

    public ArrayList<Comment> getCommentList() {
        return CommentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        CommentList = commentList;
    }
}
