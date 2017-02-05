package myggum.openapifordaumkakao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016-11-21.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    public static List<Item> items;
    private static Context context;

    public ItemClick itemClick;
    public interface ItemClick{
        public void onClick(View view,int position);
    }

    public void setItemClick(ItemClick itemClick){
        this.itemClick=itemClick;
    }
    public ListAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kakao, parent, false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        //holder.text1.setText(items.get(position).getTitle());
        holder.text2.setText(items.get(position).getTitle());
        Glide.with(context).load(items.get(position).getImage()).into(holder.background);



        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(itemClick!=null){
                itemClick.onClick(view,position);
            }
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ListViewHolder extends RecyclerView.ViewHolder  {
        ImageView background;
        TextView text1;
        TextView text2;

        public ListViewHolder(final View v) {
            super(v);
            background = (ImageView) v.findViewById(R.id.thumb);
          //  text1 = (TextView) v.findViewById(R.id.code);
            text2 = (TextView) v.findViewById(R.id.title);
        }
    }



}

