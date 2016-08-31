package com.example.dell.woof.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dell.woof.R;
import com.example.dell.woof.ui.DogProfileActivity;

import java.util.ArrayList;

/**
 * Created by praful on 16/8/16.
 */
public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    ArrayList<Bitmap> itemListModelArrayList;
    Context context;

    public DogAdapter(Context context, ArrayList<Bitmap> itemListModelArrayList) {
        this.context = context;
        this.itemListModelArrayList = itemListModelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.dogImage.setImageBitmap(itemListModelArrayList.get(position));
            viewHolder.dogImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DogProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        viewHolder.dog_img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DogProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView dogImage;
        private ImageView dog_img_next;

        public ViewHolder(View view) {
            super(view);
            dogImage = (ImageView) view.findViewById(R.id.dog_img);
            dog_img_next = (ImageView) view.findViewById(R.id.dog_img_next);
        }
    }
}
