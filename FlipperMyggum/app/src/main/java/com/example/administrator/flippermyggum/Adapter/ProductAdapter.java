package com.example.administrator.flippermyggum.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.flippermyggum.Model.ProductList;
import com.example.administrator.flippermyggum.R;

import java.util.List;

/**
 * Created by Administrator on 2016-09-27.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    List<ProductList> pList;
    Context context;
    private static OnitemClickListener listener;
    public interface OnitemClickListener{
        void onItemClick(View itemview, int position);
    }
    public void setOnItemClickListener(OnitemClickListener listener){
        this.listener=listener;
    }
    public ProductAdapter(List<ProductList> pList, Context context) {
        this.pList = pList;
        this.context = context;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public MyViewHolder(final View itemView) {
            super(itemView);
            image= (ImageView)itemView.findViewById(R.id.productImage);
            textView1=(TextView)itemView.findViewById(R.id.productname);
            textView2=(TextView)itemView.findViewById(R.id.productname2);
            textView3=(TextView)itemView.findViewById(R.id.productnumber);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                        listener.onItemClick(itemView,getLayoutPosition());
                }
            });*/
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.productlist,parent,false);
        return new MyViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProductList productList= pList.get(position);
        holder.textView1.setText(productList.getName1());
        holder.textView2.setText(productList.getName2());
        holder.textView3.setText(productList.getName3());
        Glide.with(context).load(productList.getImage()).into(holder.image);
    }
    @Override
    public int getItemCount() {
        return pList.size();
    }
}
