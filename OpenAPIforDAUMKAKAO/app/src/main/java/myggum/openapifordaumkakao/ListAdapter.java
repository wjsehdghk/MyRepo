package myggum.openapifordaumkakao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016-11-21.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<Channel.Item> items;

    private Context context;


    public ListAdapter(List<Channel.Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kakao,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
            holder.text1.setText(items.get(position).getTitle());
            holder.text2.setText(items.get(position).getPubDate());
        Glide.with(context).load(items.get(position).getImage()).into(holder.background);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{
            ImageView background;
            TextView text1;
            TextView text2;
        public ListViewHolder(View v){
            super(v);
            background =(ImageView)v.findViewById(R.id.thumb);
            text1= (TextView)v.findViewById(R.id.code);
            text2= (TextView)v.findViewById(R.id.title);
        }






    }

}
