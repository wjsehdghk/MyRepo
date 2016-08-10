package com.example.administrator.cardviewex.Adapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.cardviewex.Model.ITem;
import com.example.administrator.cardviewex.R;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016-08-10.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {


    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemview, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    Context context;
    List<ITem> items = new ArrayList<>();
    public ItemAdapter(List<ITem> items) {
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ITem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.image.setImageResource(item.getImage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });


        }

    }


}
