package grupodobaralho.topfood_android.data.db.model;

import com.google.gson.annotations.SerializedName;

public class Text {

    @SerializedName("text")
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                '}';
    }
}
