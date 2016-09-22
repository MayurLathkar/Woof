package com.example.dell.woof.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.woof.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 28-08-2016.
 */
public class DogProfileAdapter extends RecyclerView.Adapter<DogProfileAdapter.ViewHolder> {

    private Context context;
    private String[] dogsImageList;

    public DogProfileAdapter(Context context, String[] dogImages){
        this.context = context;
        this.dogsImageList = dogImages;
    }

    @Override
    public int getItemCount() {
        return dogsImageList.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.single_dog_profile, null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(dogsImageList[position]).into(holder.dogProfile);
        holder.deleteDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView dogProfile, deleteDog;

        public ViewHolder(View itemView){
            super(itemView);
            dogProfile = (CircleImageView) itemView.findViewById(R.id.profile);
            deleteDog = (ImageView) itemView.findViewById(R.id.delete);
        }
    }


}
