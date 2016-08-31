package com.example.dell.woof.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.example.dell.woof.util.Util;

import java.util.List;

/**
 * Created by Dell on 28-08-2016.
 */
public class DogProfileAdapter extends RecyclerView.Adapter<DogProfileAdapter.ViewHolder> {

    private Context context;
    private List<Bitmap> dogsImageList;

    public DogProfileAdapter(Context context, List<Bitmap> dogImages){
        this.context = context;
        this.dogsImageList = dogImages;
    }

    @Override
    public int getItemCount() {
        if (dogsImageList.size() > 0){
            return dogsImageList.size() + 1;
        }
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.single_dog_profile, null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position < dogsImageList.size()){
            if(dogsImageList.get(position) != null)
                holder.dogProfile.setImageBitmap(dogsImageList.get(position));
        }

        holder.dogProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).startActivityForResult(Util.getPickImageChooserIntent(context), position);
            }
        });

        if (position >= 0){
            holder.deleteDog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Position: "+position+" SIze: "+dogsImageList.size(), Toast.LENGTH_SHORT).show();
                    dogsImageList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView dogProfile, deleteDog;

        public ViewHolder(View itemView){
            super(itemView);
            dogProfile = (ImageView) itemView.findViewById(R.id.addDog);
            deleteDog = (ImageView) itemView.findViewById(R.id.deleteDog);
        }
    }


}
