package com.example.dell.woof.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.woof.R;
import com.example.dell.woof.model.CartItem;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class CartItemAdapter extends BaseAdapter {
    private List<CartItem> cartItems = Collections.emptyList();
    private final Context context;

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textViewItemTitle, textViewItemMrpAmt, textViewItemAmount, textViewItemQuant;
        ImageView imageViewItemImg;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_cart_item, parent, false);
            textViewItemTitle = (TextView) convertView.findViewById(R.id.item_title);
            textViewItemMrpAmt = (TextView) convertView.findViewById(R.id.item_mrp_amt);
            textViewItemAmount = (TextView) convertView.findViewById(R.id.item_discount_amt);
            textViewItemQuant = (TextView) convertView.findViewById(R.id.item_quantity);
            imageViewItemImg = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(new ViewHolder(textViewItemTitle, textViewItemMrpAmt, textViewItemAmount, textViewItemQuant, imageViewItemImg));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            textViewItemTitle = viewHolder.textViewItemTitle;
            textViewItemMrpAmt = viewHolder.textViewItemMrpAmt;
            textViewItemAmount = viewHolder.textViewItemAmount;
            textViewItemQuant = viewHolder.textViewItemQuant;
            imageViewItemImg = viewHolder.imageViewItemImg;
        }
        final CartItem cartItem = getItem(position);
        textViewItemTitle.setText(cartItem.getProduct().getItemTitle());
        textViewItemMrpAmt.setText(cartItem.getProduct().getItemMrpAmt());
        textViewItemAmount.setText(String.valueOf(cartItem.getProduct().getItemAmount()));
        textViewItemQuant.setText(String.valueOf(cartItem.getQuantity()));
        Picasso.with(context).load(cartItem.getProduct().getItemId())
//                .placeholder()
                .resize(200, 200).into(imageViewItemImg);
        return convertView;
    }

    private static class ViewHolder {
        public final TextView textViewItemTitle;
        public final TextView textViewItemMrpAmt;
        public final TextView textViewItemAmount;
        public final TextView textViewItemQuant;
        public final ImageView imageViewItemImg;

        public ViewHolder(TextView textViewItemTitle, TextView textViewItemMrpAmt, TextView textViewItemAmount, TextView textViewItemQuant, ImageView imageViewItemImg) {
            this.textViewItemTitle = textViewItemTitle;
            this.textViewItemMrpAmt = textViewItemMrpAmt;
            this.textViewItemAmount = textViewItemAmount;
            this.textViewItemQuant = textViewItemQuant;
            this.imageViewItemImg = imageViewItemImg;
        }
    }
}
