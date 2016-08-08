package com.example.dongjun.recyclerex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        movie=new Movie("mad max", "Action", "2015");
        movies.add(movie);
        movie=new Movie("gold" , "Action" , "1981");
        movies.add(movie);

        movieAdapter.notifyDataSetChanged();


    }
}
