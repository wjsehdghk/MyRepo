package com.example.administrator.loopjasynchttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;



public class MainActivity extends AppCompatActivity {
    AsyncHttpClient client;
    final String BaseUrl = "http://192.168.1.2/login.php";
    EditText editText;
    EditText editText2;
    Button logbtn;
    PersistentCookieStore cookieStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        client = new AsyncHttpClient();



        //쿠키값 저장.
        //cookieStore = new PersistentCookieStore(getApplicationContext());
       // client.setCookieStore(cookieStore);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        editText2= (EditText)findViewById(R.id.editText2);
        logbtn = (Button)findViewById(R.id.login);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editText.getText().toString().trim(),editText2.getText().toString().trim());
            }
        });
        //get방식으로 서버에 요청
        /*
        client.get(getBaseContext(), BaseUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getBaseContext(), "test" + statusCode, Toast.LENGTH_SHORT).show();
                Log.e("fail","test" + statusCode);
                Log.d("cookie",""+cookieStore.getCookies());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e("succeess","test"+statusCode);
                Toast.makeText(getBaseContext(), "test" + statusCode, Toast.LENGTH_SHORT).show();
                Log.d("cookie",""+cookieStore.getCookies());
            }
        });
        */
        /*
        client.get(getBaseContext(), BaseUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("succeess","test");
                Toast.makeText(getBaseContext(), "test" + statusCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getBaseContext(), "test" + statusCode, Toast.LENGTH_SHORT).show();
                Log.e("asd","test");
            }
        });
*/
        /*
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
}*/
    }

    public void login(String id,String pw){
        RequestParams params= new RequestParams();
        params.put("email",id);
        params.put("password",pw);
        cookieStore = new PersistentCookieStore(getApplicationContext());
        cookieStore.clear();
        client.setCookieStore(cookieStore);
        client.post(getBaseContext(), BaseUrl, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getBaseContext(), "test" + statusCode, Toast.LENGTH_SHORT).show();
                Log.e("fail","test" + statusCode);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                try{
                    JSONObject jObj = new JSONObject(responseString);
                    boolean error=jObj.getBoolean("error");
                    if(!error){
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();

                }}
                catch (Exception e) {
                    e.printStackTrace();
                }

                Log.e("zzz","123"+responseString);
                Log.e("zzz","123"+statusCode);
                Toast.makeText(getBaseContext(), "test" + statusCode, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
