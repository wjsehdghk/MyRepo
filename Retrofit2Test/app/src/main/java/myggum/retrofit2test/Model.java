package myggum.retrofit2test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016-11-17.
 */
public class Model {
    @SerializedName("userId")
    int userId;
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Model(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
