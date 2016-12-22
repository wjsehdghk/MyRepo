package com.example.administrator.surface.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.surface.Model.VideoList;
import com.example.administrator.surface.R;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016-09-13.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> implements DraggableItemAdapter<VideoAdapter.MyViewHolder> {
    List<VideoList> vList;
    Context context;
    public VideoAdapter(List<VideoList> vList, Context context) {
        setHasStableIds(true);
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
    public class MyViewHolder extends AbstractDraggableItemViewHolder{
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
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }
    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }
    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        VideoList videoList= vList.remove(fromPosition);
        vList.add(toPosition,videoList);
        notifyItemMoved(fromPosition,toPosition);
    }
    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
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
    public long getItemId(int position) {
        return vList.get(position).id;
    }

    @Override
    public int getItemCount() {
        return vList.size();
    }
}
