package grupodobaralho.topfood_android.ui.comment.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.comment.presenter.CommentPresenter;
import grupodobaralho.topfood_android.ui.comment.presenter.ICommentPresenter;
import grupodobaralho.topfood_android.ui.commentList.view.CommentListView;

public class CommentView extends AppCompatActivity implements ICommentView, View.OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    public static final String EXTRA_RESTAURANT = "restaurant";
    public static final String EXTRA_PRODUCT = "product";
    private Restaurant restaurant;
    private Product product;
    private ProgressDialog mProgress;
    private EditText editText;
    private ImageButton imageButton;
    private ICommentPresenter presenter;
    private byte[] bImg;

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
        imageButton = findViewById(R.id.photo_comment_btn);

        imageButton.setOnClickListener(this);

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
        startActivity(new Intent(this, CommentListView.class).putExtra(EXTRA_RESTAURANT, restaurant).putExtra(EXTRA_PRODUCT, product).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
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
        editText.setError(getString(R.string.text_comment_error));
    }

    @Override
    public void onClick(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(mphoto);
            //Transforma a imagem em vetor de byte
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mphoto.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            bImg = outputStream.toByteArray();
        }
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
                if(bImg != null)
                    presenter.postComment(restaurant.getId(), product.getId(), editText.getText().toString(), bImg.toString());
                else
                    presenter.postComment(restaurant.getId(), product.getId(), editText.getText().toString(), "");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
