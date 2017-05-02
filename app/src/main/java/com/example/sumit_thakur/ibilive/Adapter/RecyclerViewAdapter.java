package com.example.sumit_thakur.ibilive.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sumit_thakur.ibilive.Constants.Constants;
import com.example.sumit_thakur.ibilive.Model.SetDataModel;
import com.example.sumit_thakur.ibilive.R;

import java.util.ArrayList;

/**
 * Recycler View adapter
 */
public class RecyclerViewAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Constants {

    private Context context;
    private ArrayList<SetDataModel> setDataModels;
    private String mMode;

    /**
     * @param context       context
     * @param setDataModels array list setData models
     * @param mMode         mode
     */
    public RecyclerViewAdapter(final Context context, final ArrayList<SetDataModel> setDataModels, final String mMode) {
        this.context = context;
        this.setDataModels = setDataModels;
        this.mMode = mMode;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        context = parent.getContext();
        View convertView;
        if (mMode.equals(MY_NETWORK)) {
            Log.e("error", "enter this");
            convertView = LayoutInflater.from(context).inflate(R.layout.row_layout_network, parent, false);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        SetDataModel obj = setDataModels.get(position);
        if (mMode.equals(MY_NETWORK)) {
            holder.tvLocation.setText(obj.getmLocation());
            holder.tvName.setText(obj.getmName());
        } else if (mMode == MY_POSTS) {
            holder.btnSendRequest.setVisibility(View.GONE);
            holder.imagePickup.setVisibility(View.GONE);
            holder.ratingBar.setVisibility(View.GONE);
            holder.tvName.setVisibility(View.GONE);

            holder.tvInfo.setText(obj.getmInfo());
            holder.tvLocation.setText(obj.getmLocation());
            holder.tvReview.setText(obj.getmReviews());
            holder.tvCityName.setText(obj.getmCityName());
            holder.tvTime.setText(obj.getmTime());
        } else {
            holder.tvInfo.setText(obj.getmInfo());
            holder.tvLocation.setText(obj.getmLocation());
            holder.tvReview.setText(obj.getmReviews());
            holder.tvCityName.setText(obj.getmCityName());
            holder.tvTime.setText(obj.getmTime());
            holder.tvName.setText(obj.getmName());
            holder.aSwitch.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return setDataModels.size();
    }

    @Override
    public void extra() {

    }

    /**
     * view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCityName, tvLocation, tvName, tvTime, tvReview, tvInfo;
        private Switch aSwitch;
        private Button btnSendRequest;
        private ImageView imagePickup;
        private RatingBar ratingBar;

        /**
         * constructor
         *
         * @param itemView item view
         */
        public ViewHolder(final View itemView) {
            super(itemView);
            if (mMode.equals(MY_NETWORK)) {
                Log.e("error", "sucess3");
                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvLocation = (TextView) itemView.findViewById(R.id.tvLocations);
            } else {
                tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
                tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                tvTime = (TextView) itemView.findViewById(R.id.tvTime);
                tvReview = (TextView) itemView.findViewById(R.id.tvReviews);
                tvInfo = (TextView) itemView.findViewById(R.id.tvInfo);
                aSwitch = (Switch) itemView.findViewById(R.id.switchSave);
                btnSendRequest = (Button) itemView.findViewById(R.id.btnSend);
                imagePickup = (ImageView) itemView.findViewById(R.id.pic_image);
                ratingBar = (RatingBar) itemView.findViewById(R.id.rbRating);
            }
        }
    }
}
