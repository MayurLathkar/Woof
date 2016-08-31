package com.example.dell.woof.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.interfaces.RecyclerViewClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by praful on 12/8/16.
 */
public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder> {
    ArrayList<com.example.dell.woof.model.Product> itemListModelArrayList;
    Context context;
    RecyclerViewClick recyclerViewClick;

    public ShopItemAdapter(Context context, ArrayList<com.example.dell.woof.model.Product> itemListModelArrayList, RecyclerViewClick recyclerViewClick) {
        this.context = context;
        this.itemListModelArrayList = itemListModelArrayList;
        this.recyclerViewClick = recyclerViewClick;
    }

    @Override
    public ShopItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopItemAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.tv_android.setText(itemListModelArrayList.get(position).getItemTitle());
        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClickRecycler(view, position);
            }
        });
        viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewClick.onClickRecycler(view, position);
            }
        });
        Picasso.with(context).load(itemListModelArrayList.get(position).getItemId())
//                .placeholder()
                .resize(400, 400).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return itemListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_android;
        private ImageView img_android, addToCart;

        public ViewHolder(View view) {
            super(view);
            tv_android = (TextView) view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            addToCart = (ImageView) view.findViewById(R.id.add_to_cart);
        }
    }
}
