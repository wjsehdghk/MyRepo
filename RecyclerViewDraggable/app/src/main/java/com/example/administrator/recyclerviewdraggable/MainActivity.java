package com.example.administrator.recyclerviewdraggable;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<ImageThumb> ImageList = new ArrayList<>();
    RecyclerView.Adapter adapter;


    RecyclerViewDragDropManager dragMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareBackground();
        dragMgr= new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_horizon);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));
        adapter= new ThumbAdapter(ImageList,getBaseContext());
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));
        dragMgr.attachRecyclerView(recyclerView);

    }


    static class ImageThumb {
        int image;
        public final long id;
        public ImageThumb(int image,long id) {
            this.image = image;
            this.id=id;
        }


        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }



    static class MyViewHolder extends AbstractDraggableItemViewHolder {

         ImageView image;

        public MyViewHolder(View view){
            super(view);
            image = (ImageView)view.findViewById(R.id.Imagethumb);

        }
    }


    static class ThumbAdapter extends RecyclerView.Adapter<MyViewHolder> implements DraggableItemAdapter<MyViewHolder> {


        private List<ImageThumb> ImageList;
        private Context context;


        public ThumbAdapter(List<ImageThumb> imageList, Context context) {
            setHasStableIds(true);
            ImageList = imageList;
            this.context = context;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageitem,parent,false);
            return new MyViewHolder(itemView);

        }

        @Override
        public long getItemId(int position) {
            return ImageList.get(position).id;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            ImageThumb imageThumb=ImageList.get(position);
            Glide.with(context).load(imageThumb.getImage()).into(holder.image);
        }
        @Override
        public int getItemCount() {
            return ImageList.size();
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

            ImageThumb imageThumb= ImageList.remove(fromPosition);
            ImageList.add(toPosition,imageThumb);
            notifyItemMoved(fromPosition,toPosition);

        }

        @Override
        public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
            return true;
        }
    }




    private void prepareBackground() {
        int[] covers = new int[]{
                R.drawable.zzz,
                R.drawable.ha,
                R.drawable.mina,
                R.drawable.jj,
                R.drawable.ss
        };
        ImageThumb a = new ImageThumb(covers[0],1);
        ImageList.add(a);
        a = new ImageThumb(covers[1],2);
        ImageList.add(a);
        a = new ImageThumb(covers[2],3);
        ImageList.add(a);
        a = new ImageThumb(covers[3],4);
        ImageList.add(a);
        a = new ImageThumb(covers[4],5);
        ImageList.add(a);
    }
}
