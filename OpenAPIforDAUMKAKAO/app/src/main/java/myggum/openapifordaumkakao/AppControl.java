package myggum.openapifordaumkakao;

import android.app.Application;
import android.util.Log;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Administrator on 2016-11-29.
 */
public class AppControl extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TESTGG","1");

        Typekit.getInstance()
                .addItalic(Typekit.createFromAsset(this,"fonts/NanumPen.ttf"))
                .addNormal(Typekit.createFromAsset(this,"fonts/NanumBarunpen.ttf"))
                .addBold(Typekit.createFromAsset(this,"fonts/NanumBarunpenB.ttf"))
                .addCustom1(Typekit.createFromAsset(this,"fonts/NanumMyeongjo.ttf"));

    }
}
