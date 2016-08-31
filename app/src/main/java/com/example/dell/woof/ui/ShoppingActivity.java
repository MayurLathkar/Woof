package com.example.dell.woof.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.adapters.GridAutoSpaceAdapter;
import com.example.dell.woof.adapters.ShopItemAdapter;
import com.example.dell.woof.interfaces.RecyclerViewClick;
import com.example.dell.woof.model.CartHelper;
import com.example.dell.woof.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by praful on 11/8/16.
 */
public class ShoppingActivity extends AppCompatActivity implements RecyclerViewClick {

    public static final ArrayList<Product> PRODUCT_LIST = new ArrayList<Product>();
    public static final Product PRODUCT1 = new Product(R.drawable.beer, BigDecimal.valueOf(199.996), "Beer", "", "Rs. 400", "", "");
    public static final Product PRODUCT2 = new Product(R.drawable.bun, BigDecimal.valueOf(449.9947), "Bun", "", "Rs. 200", "", "");
    public static final Product PRODUCT3 = new Product(R.drawable.fried_chicken, BigDecimal.valueOf(319.998140), "Fried Chicken", "Chicken", "Rs. 100", "", "");
    public static final Product PRODUCT4 = new Product(R.drawable.beer, BigDecimal.valueOf(199.996), "Beer", "", "Rs. 400", "", "");
    public static final Product PRODUCT5 = new Product(R.drawable.bun, BigDecimal.valueOf(449.9947), "Bun", "", "Rs. 200", "", "");
    public static final Product PRODUCT6 = new Product(R.drawable.fried_chicken, BigDecimal.valueOf(319.998140), "Fried Chicken", "Chicken", "Rs. 100", "", "");
    public static final Product PRODUCT7 = new Product(R.drawable.beer, BigDecimal.valueOf(199.996), "Beer", "", "Rs. 400", "", "");
    public static final Product PRODUCT8 = new Product(R.drawable.bun, BigDecimal.valueOf(449.9947), "Bun", "", "Rs. 200", "", "");
    public static final Product PRODUCT9 = new Product(R.drawable.fried_chicken, BigDecimal.valueOf(319.998140), "Fried Chicken", "Chicken", "Rs. 100", "", "");
    public static final Product PRODUCT10 = new Product(R.drawable.beer, BigDecimal.valueOf(199.996), "Beer", "", "Rs. 400", "", "");
    public static final Product PRODUCT11 = new Product(R.drawable.bun, BigDecimal.valueOf(449.9947), "Bun", "", "Rs. 200", "", "");
    public static final Product PRODUCT12 = new Product(R.drawable.fried_chicken, BigDecimal.valueOf(319.998140), "Fried Chicken", "Chicken", "Rs. 100", "", "");
    public static final Product PRODUCT13 = new Product(R.drawable.beer, BigDecimal.valueOf(199.996), "Beer", "", "Rs. 400", "", "");
    public static final Product PRODUCT14 = new Product(R.drawable.bun, BigDecimal.valueOf(449.9947), "Bun", "", "Rs. 200", "", "");
    public static final Product PRODUCT15 = new Product(R.drawable.fried_chicken, BigDecimal.valueOf(319.998140), "Fried Chicken", "Chicken", "Rs. 100", "", "");

    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
        PRODUCT_LIST.add(PRODUCT4);
        PRODUCT_LIST.add(PRODUCT5);
        PRODUCT_LIST.add(PRODUCT6);
        PRODUCT_LIST.add(PRODUCT7);
        PRODUCT_LIST.add(PRODUCT8);
        PRODUCT_LIST.add(PRODUCT9);
        PRODUCT_LIST.add(PRODUCT10);
        PRODUCT_LIST.add(PRODUCT11);
        PRODUCT_LIST.add(PRODUCT12);
        PRODUCT_LIST.add(PRODUCT13);
        PRODUCT_LIST.add(PRODUCT14);
        PRODUCT_LIST.add(PRODUCT15);
    }

    int width;
    RecyclerView recyclerView;
    ShopItemAdapter adapter;
    boolean thumbnail = false;
    GridAutoSpaceAdapter layoutManager;
    RecyclerViewClick recyclerViewClick = ShoppingActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        layoutManager = new GridAutoSpaceAdapter(this, (width - 100) / 2);
        recyclerView = (RecyclerView) findViewById(R.id.shop_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ShopItemAdapter(getApplicationContext(), PRODUCT_LIST, recyclerViewClick);
        recyclerView.setAdapter(adapter);

        final TextView fab = (TextView) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
//                if (!thumbnail) {
//                    thumbnail = true;
//                    layoutManager = new GridAutoSpaceAdapter(ShoppingActivity.this, width);
//                } else {
//                    thumbnail = false;
//                    layoutManager = new GridAutoSpaceAdapter(ShoppingActivity.this, (width - 100) / 3);
//                }
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClickRecycler(View view, int position) {
        Product product = PRODUCT_LIST.get(position);
        switch (view.getId()) {
            case R.id.img_android:
                Toast.makeText(ShoppingActivity.this, "This is " + product.getItemTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_android:
                com.example.dell.woof.model.Cart cart = CartHelper.getCart();
                cart.add(product, 1);
                Toast.makeText(ShoppingActivity.this, product.getItemTitle() + " is added to cart", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
