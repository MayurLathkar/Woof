package com.example.dell.woof.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.dell.woof.R;
import com.example.dell.woof.model.DoctorsDetails;
import com.example.dell.woof.ui.CalenderActivity;
import com.example.dell.woof.ui.DoctorDetailsActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 09-08-2016.
 */
public class ExploreViewAdapter extends RecyclerView.Adapter<ExploreViewAdapter.ViewHolder> {

    private Context mContext;
    private SwipeLayout layout;
    private ArrayList<DoctorsDetails> doctorsDetails;

    public ExploreViewAdapter(Context context, ArrayList<DoctorsDetails> doctorsDetails){
        this.mContext = context;
        this.doctorsDetails = doctorsDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_doctor_clinic, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(params);
        layout = (SwipeLayout) itemView.findViewById(R.id.swipe);
        layout.setShowMode(SwipeLayout.ShowMode.PullOut);
        View bookLayout = itemView.findViewById(R.id.bookApt);
        bookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CalenderActivity.class);
                mContext.startActivity(intent);
            }
        });
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(doctorsDetails.get(position).getDtrName());
        holder.details.setText(doctorsDetails.get(position).getDtrDetails()+"\n"+doctorsDetails.get(position).getDtrAddress()+"\n"+doctorsDetails.get(position).getDtrNumber());
        holder.profile.setImageURI(Uri.parse(doctorsDetails.get(position).getDtrImage()));

        layout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DoctorDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor", doctorsDetails.get(position));
                intent.putExtra("doctorBundle", bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (doctorsDetails.size()>0)
            return doctorsDetails.size();
        else  return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private View currentView;
        private TextView title;
        private TextView details;
        private CircleImageView profile;

        public ViewHolder(View itemView){
            super(itemView);
            currentView = itemView;
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            details = (TextView) itemView.findViewById(R.id.tvDetails);
            profile = (CircleImageView) itemView.findViewById(R.id.ivProfile);
        }
    }
}
