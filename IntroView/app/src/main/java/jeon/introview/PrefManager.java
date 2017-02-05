package jeon.introview;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 전동화 on 2017-02-06.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME= "JEON";
    private static final String IS_FIRST_TIME = "IsFirstTimeLaunch";


    public PrefManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME,isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME,true);
    }
}
