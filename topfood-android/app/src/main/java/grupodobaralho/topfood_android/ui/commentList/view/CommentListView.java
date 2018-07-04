package grupodobaralho.topfood_android.ui.commentList.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import grupodobaralho.topfood_android.R;
import grupodobaralho.topfood_android.data.db.model.Product;
import grupodobaralho.topfood_android.data.db.model.Restaurant;
import grupodobaralho.topfood_android.ui.comment.view.CommentView;
import grupodobaralho.topfood_android.ui.commentList.presenter.CommentListPresenter;
import grupodobaralho.topfood_android.ui.commentList.presenter.ICommentListPresenter;
import grupodobaralho.topfood_android.ui.login.view.LoginView;

public class CommentListView extends AppCompatActivity implements ICommentListView, View.OnClickListener {

    public static final String EXTRA_RESTAURANT = "restaurant";
    public static final String EXTRA_PRODUCT = "product";
    private Restaurant restaurant;
    private Product product;
    private ICommentListPresenter presenter;
    private CommentListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            restaurant = (Restaurant) intentFromList.getSerializableExtra(EXTRA_RESTAURANT);
            product = (Product) intentFromList.getSerializableExtra(EXTRA_PRODUCT);
        }

        TextView tvProductTitle = findViewById(R.id.product_title_in_comment_list_tv);
        TextView tvProductPrice = findViewById(R.id.product_price_in_comment_list_tv);

        tvProductTitle.setText(product.getName());
        tvProductPrice.setText(product.getPrice());

        if(presenter == null)
            presenter = new CommentListPresenter();
        presenter.setView(this);
        presenter.setIds(restaurant.getId(), product.getId());
        presenter.listAllComments();

        findViewById(R.id.comment_floatingActionButton).setOnClickListener(this);
    }

    @Override
    public void showComments() {
        //Variaveis necessarias para trabalhar com recyclerview
        RecyclerView rvComment = findViewById(R.id.rv_comments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvComment.setLayoutManager(layoutManager);
        rvComment.setVisibility(View.VISIBLE);

        adapter = new CommentListAdapter(this, presenter, presenter.getComments());
        rvComment.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.pb_comments).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        findViewById(R.id.pb_comments).setVisibility(View.GONE);
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, CommentView.class).putExtra(EXTRA_RESTAURANT, restaurant).putExtra(EXTRA_PRODUCT, product));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);

        if(!presenter.isUserLogged()) {
            menu.findItem(R.id.action_logout).setTitle("Fazer Login");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        switch (itemId) {

            case R.id.action_logout:
                if (!presenter.isUserLogged()) {
                    startActivity(new Intent(this, LoginView.class));
                } else {
                    presenter.makeLogout();
                    startActivity(new Intent(this, CommentListView.class).putExtra(EXTRA_RESTAURANT, restaurant).putExtra(EXTRA_PRODUCT, product));
                    finish();
                }
                break;

            case android.R.id.home:
                this.onBackPressed();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
