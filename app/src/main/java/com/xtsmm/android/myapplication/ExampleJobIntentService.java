package com.xtsmm.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService {


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("serviceeeee","ExampleJobIntentService");

        String input=intent.getStringExtra("inputExtra");
        for(int i=0;i<10;i++){
            Log.d("serviceeeee",input+" - "+i);
            if(isStopped())return;
            SystemClock.sleep(1000);
        }
    }

    static void enqueueWork(Context context,Intent work){
        enqueueWork(context,ExampleJobIntentService.class,123,work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("serviceeeee","onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("serviceeeee","onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.d("serviceeeee","onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
