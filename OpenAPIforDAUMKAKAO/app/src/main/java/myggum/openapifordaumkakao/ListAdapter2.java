package myggum.openapifordaumkakao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016-11-22.
 */
public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ListViewHolder> {

    private List<Channel.Item> itemList;
    private Context context;

    public ListAdapter2(List<Channel.Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daum,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.text1.setText(itemList.get(position).getTitle());
        holder.text2.setText(itemList.get(position).getPubDate());
        Glide.with(context).load(itemList.get(position).getImage()).into(holder.background);

        String str = itemList.get(position).getTitle();
        String str1 = itemList.get(position).getPubDate();
        Log.d("TAG","msg-->" + str + " " + str1  );
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView background;
        TextView text1;
        TextView text2;
        public ListViewHolder(View v){
            super(v);
            background =(ImageView)v.findViewById(R.id.daumimage);
            text1= (TextView)v.findViewById(R.id.daumtext);
            text2= (TextView)v.findViewById(R.id.daumtext2);
        }






    }

}
