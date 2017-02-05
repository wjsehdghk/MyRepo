package myggum.okhttpclienttest;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016-11-11.
 */
public class RequestBuilder { //post를 할때, 정보를 넘기기 위한 Request바디.



    public static RequestBody LoginBody(String username, String password, String token){
        return new FormBody.Builder()
                .add("action","login")
                .add("format","json")
                .add("username",username)
                .add("password",password)
                .add("logintoken",token)
                .build();
    }

    public static MultipartBody upload(String title, String imageformat, String token, File file){


        MediaType MEDIA_TYPE= MediaType.parse("image/"+imageformat); //"image/png"


        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("action","upload")
                .addFormDataPart("format","json")
                .addFormDataPart("filename",title+"."+imageformat) //title.png
                .addFormDataPart("token",token)
                .addFormDataPart("file","...",RequestBody.create(MEDIA_TYPE,file))
                .build();
    }



    public static HttpUrl buildURL(){
        return new HttpUrl.Builder()
                .scheme("https")
                .host("api.github.com")
                .addPathSegment("users")
                .addPathSegment("codepath")
                .build(); //https://api.github.com/users/codepath

    }

}
