package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by zulfikaranshari on 01/04/2018.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ImageViewHolder> {
    public String userMail;
    private ArrayList<UploadModel> mTimeline;
    private Context mContext;
    private UploadModel mCurrentUpload;

    TimelineAdapter(Context context, ArrayList<UploadModel> timeline){
        mTimeline = timeline;
        mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_timeline, parent, false);
        return new TimelineAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimelineAdapter.ImageViewHolder holder, int position) {
        mCurrentUpload = mTimeline.get(position);

        holder.mTitle.setText(mCurrentUpload.getmTitle());
        holder.mCaption.setText(mCurrentUpload.getmCaption());
        holder.mEmail.setText(mCurrentUpload.getmEmail());
        Picasso.get()
                .load(mCurrentUpload.getmUrl())
                .into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return mTimeline.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mEmail;
        private TextView mTitle;
        private TextView mCaption;
        private ImageView mImage;

        public ImageViewHolder(View itemView) {
            super(itemView);

            mEmail = itemView.findViewById(R.id.email);
            mTitle = itemView.findViewById(R.id.titlePost);
            mCaption = itemView.findViewById(R.id.captionPost);
            mImage = itemView.findViewById(R.id.imageViewUploaded);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            String email = mTimeline.get(position).toString();
            String uri = mTimeline.get(position).toString();
            String title = mTimeline.get(position).toString();
            String caption = mTimeline.get(position).toString();
            Intent x = new Intent(view.getContext(), PostDetail.class);
            x.putExtra("email", email);
            x.putExtra("uri", uri);
            x.putExtra("title", title);
            x.putExtra("caption", caption);
            view.getContext().startActivity(x);
        }
    }
}
