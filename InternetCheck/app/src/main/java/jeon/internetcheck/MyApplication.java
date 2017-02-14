package jeon.internetcheck;

import android.app.Application;

/**
 * Created by 전동화 on 2017-02-14.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance=this;
    }
    public static synchronized MyApplication getInstance(){
        return mInstance;
    }
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener){
        ConnectivityReceiver.connectivityReceiverListener =listener;

    }
}
