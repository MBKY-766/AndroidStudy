package com.example.shopping01.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    private static SharedUtil util;
    private SharedPreferences preferences;

    public static SharedUtil getInstance(Context ctx){
        if(util == null){
            util = new SharedUtil();
            util.preferences = ctx.getSharedPreferences("shopping",Context.MODE_PRIVATE);
        }
        return util;
    }

    public void writeBoolean(String key ,boolean value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public boolean readBoolean(String key,boolean defaultValue){
        return preferences.getBoolean(key,defaultValue);
    }

}
