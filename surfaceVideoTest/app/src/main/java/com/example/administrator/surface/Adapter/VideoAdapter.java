package com.example.administrator.surface.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.surface.Model.VideoList;
import com.example.administrator.surface.R;

import java.util.List;

/**
 * Created by Administrator on 2016-09-13.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {


    List<VideoList> vList;
    Context context;

    public VideoAdapter(List<VideoList> vList, Context context) {
        this.vList = vList;
        this.context = context;
    }


    private static OnitemClickListener listener;

    public interface OnitemClickListener{
        void onItemClick(View itemview, int position);
    }



    public void setOnItemClickListener(OnitemClickListener listener){
        this.listener=listener;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;

        public MyViewHolder(final View itemView){
            super(itemView);
            imageView= (ImageView)itemView.findViewById(R.id.Thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null)
                        listener.onItemClick(itemView,getLayoutPosition());
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.videolist,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        VideoList videoList = vList.get(position);
        holder.imageView.setImageBitmap(videoList.getThumbImage());


    }

    @Override
    public int getItemCount() {
        return vList.size();
    }
}
