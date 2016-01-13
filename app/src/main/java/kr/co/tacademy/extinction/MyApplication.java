package kr.co.tacademy.extinction;

import android.app.Application;
import android.content.Context;

/**
 * Created by samsung on 2015-12-09.
 */
public class MyApplication extends Application
{
   private static Context  myContext;
    @Override
    public void onCreate(){
        super.onCreate();
        myContext = this;
    }
    public static Context getMyContext() {
        return myContext;

    }
}
