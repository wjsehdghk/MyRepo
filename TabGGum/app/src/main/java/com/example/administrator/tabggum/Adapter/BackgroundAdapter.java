package com.example.administrator.tabggum.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.tabggum.Model.BackgroundImage;
import com.example.administrator.tabggum.R;

import java.util.List;

/**
 * Created by Administrator on 2016-08-17.
 */
public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.MyViewHolder> {

    private Context mContext;
    private List<BackgroundImage> ImageList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title;

        public MyViewHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.title);
            image=(ImageView)view.findViewById(R.id.image);
        }
    }
    public BackgroundAdapter(Context mContext, List<BackgroundImage> imageList) {
        this.mContext = mContext;
        ImageList = imageList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.background,parent,false);
        return new MyViewHolder(itemView);
}

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BackgroundImage image = ImageList.get(position);
        holder.title.setText(image.getTitle());
        Glide.with(mContext).load(image.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return ImageList.size();
    }
}
