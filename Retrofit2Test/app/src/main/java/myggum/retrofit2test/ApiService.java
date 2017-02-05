package myggum.retrofit2test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016-11-17.
 */
public interface ApiService {


    @GET("posts")
    Call<List<Model>> getUser(@Query("userId") int userid);


    @POST("posts")
    Call<Model> postUser(@Body Model model);

    @FormUrlEncoded
    @POST("posts")
    Call<Model> postUser2(@Field("userId") int userId, @Field("id") int id,
                          @Field("title") String title, @Field("body") String body);

/*
   [
    {
        "userId": 1,
            "id": 1,
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit
            molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    },
    {
        "userId": 1,
            "id": 2,
            "title": "qui est esse",
            "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque
            \nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis
            \nqui aperiam non debitis possimus qui neque nisi nulla"
    }
]
*/


    @FormUrlEncoded
    @PUT("posts/1")
        //posts/1 경로에 업데이트 한다.
    /*
    posts/1
    {
        "userId": 1,
            "id": 1,
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\
            nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    }
    */

    Call<Model> update(@Field("userId") int userId, @Field("id") int id,
                       @Field("title") String title, @Field("body") String body);

    /*
    {
        "userId": 1,
            "id": 1,
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam
            \nnostrum rerum est autem sunt rem eveniet architecto"
    }
    */


}
