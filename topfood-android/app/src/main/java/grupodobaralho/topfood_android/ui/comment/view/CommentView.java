package grupodobaralho.topfood_android.ui.comment.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.comment.presenter.CommentPresenter;
import grupodobaralho.topfood_android.ui.comment.presenter.ICommentPresenter;
import grupodobaralho.topfood_android.ui.commentList.view.CommentListView;

public class CommentView extends AppCompatActivity implements ICommentView, View.OnClickListener {

    public static final String EXTRA_RESTAURANT = "restaurant";
    public static final String EXTRA_PRODUCT = "product";
    private Restaurant restaurant;
    private Product product;
    private ProgressDialog mProgress;
    private EditText editText;
    private ICommentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            restaurant = (Restaurant) intentFromList.getSerializableExtra(EXTRA_RESTAURANT);
            product = (Product) intentFromList.getSerializableExtra(EXTRA_PRODUCT);
        }

        TextView tvProductTitle = findViewById(R.id.product_title_in_comment_tv);
        TextView tvProductPrice = findViewById(R.id.product_price_in_comment_tv);
        editText = findViewById(R.id.comment_edit_text);
        findViewById(R.id.photo_comment_btn).setOnClickListener(this);

        tvProductTitle.setText(product.getName());
        tvProductPrice.setText(product.getPrice());

        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processando...");
        mProgress.setMessage("Por favor, espere...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        presenter = new CommentPresenter(this);


    }

    @Override
    public void backToCommentList() {
        startActivity(new Intent(this, CommentListView.class).putExtra(EXTRA_RESTAURANT, restaurant).putExtra(EXTRA_PRODUCT, product));
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void setEditTextError() {
        editText.setError(getString(R.string.email_error));
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_comment, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        switch (itemId) {
            case android.R.id.home:
                this.onBackPressed();
                finish();
                return true;
            case R.id.comment_post_btn:
                presenter.postComment(restaurant.getId(), product.getId(), editText.getText().toString(), "");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
