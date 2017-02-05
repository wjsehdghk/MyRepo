package myggum.retrofitapicall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import java.util.List;

import myggum.retrofitapicall.adapter.MoviesAdapter;
import myggum.retrofitapicall.model.Movie;
import myggum.retrofitapicall.model.MoviesResponse;
import myggum.retrofitapicall.rest.ApiClient;
import myggum.retrofitapicall.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String tag = MainActivity.class.getSimpleName();
    private final static String API_KEY = "107f6b80179192bc4ed286fef9f06937";
    RecyclerView recyclerView;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(API_KEY.isEmpty()){
            Toast.makeText(getBaseContext(),"APIKEY가 없어요",Toast.LENGTH_SHORT).show();
            return;
        }

        apiInterface= ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call= apiInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                int statuscode = response.code();
                List<Movie> movieList = response.body().getResults();
               recyclerView.setAdapter(new MoviesAdapter(movieList, R.layout.list_item_movie,getBaseContext()));
                Toast.makeText(getBaseContext(),"statuscode = " +statuscode,Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });

    }
}