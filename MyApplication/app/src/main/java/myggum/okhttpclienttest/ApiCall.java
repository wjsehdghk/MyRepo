package myggum.okhttpclienttest;
import java.io.IOException;
import java.io.Reader;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016-11-11.
 */
public class ApiCall  {
    public static String GET(OkHttpClient client, HttpUrl url) throws IOException{
        Request request=new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    public static Reader GETUSER(OkHttpClient client, HttpUrl url) throws IOException{
        Request request=new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().charStream(); //Gson 을 이용하여 파싱

    }


    public static String POST(OkHttpClient client, HttpUrl url, RequestBody body)throws IOException {
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
