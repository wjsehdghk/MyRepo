package com.example.administrator.loopjasynchttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    AsyncHttpClient client;
    final String BaseUrl = "http://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new AsyncHttpClient();

        //쿠키값 저장.
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);



        //get방식으로 서버에 요청
        client.get(getBaseContext(),BaseUrl,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Toast.makeText(getBaseContext(),""+statusCode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getBaseContext(),""+statusCode,Toast.LENGTH_SHORT).show();
            }
        });

        RequestParams params = new RequestParams();
        params.put("key","value");
        params.put("key2",10);

        //post방식으로 RequestParams에 저장한 값들을 서버에 전달.
        client.post(getBaseContext(),BaseUrl,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getBaseContext(),""+statusCode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getBaseContext(),""+statusCode,Toast.LENGTH_SHORT).show();
            }
        });
        List<String> list = new ArrayList<>();
        list.add(1,"key");
        RequestParams params1 = new RequestParams(list);
        //List형식으로 값을 저장후 RequestParams 객체에 저장.

        //Post로 값들을 전달..

        client.post(getBaseContext(), BaseUrl, params1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getBaseContext(),""+statusCode,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getBaseContext(),""+statusCode,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
