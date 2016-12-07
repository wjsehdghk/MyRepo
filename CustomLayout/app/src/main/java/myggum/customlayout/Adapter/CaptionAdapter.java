package myggum.customlayout.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import myggum.customlayout.Model.TextAttr;
import myggum.customlayout.R;

/**
 * Created by Administrator on 2016-12-06.
 */

public class CaptionAdapter extends RecyclerView.Adapter<CaptionAdapter.CaptionViewHolder>{

    Context context;
    List<TextAttr> textAttrs;
    private static OnitemClickListener listener;
    public interface OnitemClickListener{
        void onItemClick(View itemview,int position);
    }
    public void setOnItemClickListener(OnitemClickListener listener){
        this.listener=listener;
    }
    static class CaptionViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public CaptionViewHolder(final View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.textattrimage);
            text=(TextView)itemView.findViewById(R.id.textattrname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                        listener.onItemClick(itemView,getLayoutPosition());
                }
            });
        }
    }
    public CaptionAdapter(Context context, List<TextAttr> textAttrs) {
        this.context = context;
        this.textAttrs = textAttrs;
    }
    @Override
    public CaptionAdapter.CaptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textattr,parent,false);
        return new CaptionViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CaptionAdapter.CaptionViewHolder holder, int position) {

        holder.text.setText(textAttrs.get(position).getTextattrname());
        Glide.with(context).load(textAttrs.get(position).getImage()).into(holder.image);

    }
    @Override
    public int getItemCount() {
        return textAttrs.size();
    }
}
