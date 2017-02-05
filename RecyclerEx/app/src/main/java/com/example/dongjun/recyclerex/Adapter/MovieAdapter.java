package com.example.dongjun.recyclerex.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.dongjun.recyclerex.Model.Movie;
import com.example.dongjun.recyclerex.R;

import java.util.ArrayList;

/**
 * Created by dongjun on 2016-08-08.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

ArrayList<Movie> movies;


    private static OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View itemview,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title,year,genre;
        public MyViewHolder(final View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            genre = (TextView) itemView.findViewById(R.id.genre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                        listener.onItemClick(itemView,getLayoutPosition());
                }
            });
        }
    }

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movieview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(movies.get(position).getTitle());
        holder.genre.setText(movies.get(position).getGenre());
        holder.year.setText(movies.get(position).getYaer());


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
