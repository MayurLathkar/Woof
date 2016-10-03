package com.example.dell.woof.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dell.woof.R;
import com.example.dell.woof.ui.BaseRequestClass;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 28-08-2016.
 */
public class DogProfileAdapter extends RecyclerView.Adapter<DogProfileAdapter.ViewHolder> {

    private Context context;
    private String[] dogsImageList;
    private String petId;

    public DogProfileAdapter(Context context, String petId, String[] dogImages){
        this.context = context;
        this.petId = petId;
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
                deleteDogImage(position);
                Toast.makeText(context, "Delete "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteDogImage(int position){
        HashMap<String, String> params = new HashMap<>();
        params.put("img_url", dogsImageList[0]);
        com.android.volley.Response.Listener<JsonObject> listener = new Response.Listener<JsonObject>() {
            @Override
            public void onResponse(JsonObject response) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        };

        BaseRequestClass.deleteDogImage(context, petId, params, listener, errorListener);
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
