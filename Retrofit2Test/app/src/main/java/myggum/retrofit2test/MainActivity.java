package myggum.retrofit2test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView text;
    ApiService apiService;
    Retrofit retrofit;
    Button button;
    Button button2;
    String str;
    List<Model> modelList;
    Model model1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        text = (TextView) findViewById(R.id.text1);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Model>> call = apiService.getUser(1);

                call.enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        modelList = response.body();
                        int r = response.code();

                        Toast.makeText(getBaseContext(), " " + r + str, Toast.LENGTH_SHORT).show();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText("" + modelList.get(0).getTitle());
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        String tr = t.getLocalizedMessage();
                        Toast.makeText(getBaseContext(), " " + tr, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Call<Model> call = apiService.update(10,10,"zzzz","zzzzz");
                // 업데이트 메소드

                //Call<Model> call = apiService.postUser2(1, 101, "asd", "asdasd");
                // 생성 메소드

                Model model = new Model(11,1,"jeon","donghwa");
                // 생성 메소드 --> 객체를 생성해서 보낸다.
                Call<Model> call = apiService.postUser(model);

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        int code = response.code();
                        Toast.makeText(getBaseContext(), "" + code, Toast.LENGTH_LONG).show();
                        model1 = response.body();
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(" " + model1.getId() + " " + model1.getTitle() + " "
                                        + model1.getUserId() + "" + model1.getBody());
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        String tr = t.getLocalizedMessage();
                        Toast.makeText(getBaseContext(), " " + tr, Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


    }
}
