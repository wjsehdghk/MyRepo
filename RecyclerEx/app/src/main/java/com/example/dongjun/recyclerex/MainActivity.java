package com.example.dongjun.recyclerex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.dongjun.recyclerex.Adapter.DividerItemDecoration;
import com.example.dongjun.recyclerex.Adapter.MovieAdapter;
import com.example.dongjun.recyclerex.Model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies= new ArrayList<>();
    RecyclerView recyclerView;
    Movie movie;
    MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        movieAdapter = new MovieAdapter(movies);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        RecyclerView.ItemDecoration itemDecoration=new
               DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        movie=new Movie("mad max", "Action", "2015");
        movies.add(movie);
        movie=new Movie("gold" , "Action" , "1981");
        movies.add(movie);
        movie= new Movie("titaic" ,"melo", "1996");
        movies.add(movie);
        movie=new Movie("darknight","Action","2008");
        movies.add(movie);
        movie=new Movie("Busan","Action","2016");
        movies.add(movie);
        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                String title=movies.get(position).getTitle();
                Toast.makeText(getApplicationContext(),title,Toast.LENGTH_LONG).show();
            }
        });
        movieAdapter.notifyDataSetChanged();
    }
}
