package com.xtsmm.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.xtsmm.library.android.json.OnEventListener;
import com.xtsmm.library.android.json.xJson4Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class MyJobIntentService extends JobIntentService {

    private  boolean mIsRandomGenertorOn;
    public static void enqueueWork(Context context,Intent intent){
        enqueueWork(context,MyJobIntentService.class,101,intent);
    }
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("xTechLog","onHandleWork, Thread id:"+Thread.currentThread().getId());
        mIsRandomGenertorOn=true;
        startRandomNumberGenerator(intent.getStringExtra("starter"));
    }

    private void startRandomNumberGenerator(String starterIdentifier) {
        while (mIsRandomGenertorOn) {
            try {
                Thread.sleep(10000);
                if (mIsRandomGenertorOn) {

                    getJSON();
                } else {

                    return;
                }
            } catch (Exception e) {
                Log.d("xTechLog", e.getMessage());
            }
        }
    }


    private void getJSON(){
        JSONArray headerJArray = new JSONArray();
        JSONObject headerJObject = new JSONObject();
        try {
            headerJObject.put("User-Agent", "UA-AUTH-STRING");
        } catch (JSONException e) {
            Log.d("xTechLog", e.getMessage());
        }
        headerJArray.put(headerJObject);

        new xJson4Service(getApplicationContext()).request("get", "https://............json", headerJArray,null, new OnEventListener<String>() {
            @Override
            public void onSuccess(String json) {
                //Log.d("xTechLog", json);
                writeToFile("live.json",json);
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("xTechLog", e.getMessage());
            }
        });

    }

    private void writeToFile(String name,String data) {
        try {
            String basePath = Environment.getExternalStorageDirectory().toString() + "/Android/data/" +
                    getPackageName().toString();
            File myFile = new File(basePath+"/"+name);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append(data.replaceAll("[\n\r]", ""));
            myOutWriter.close();
            fOut.close();
            //Log.e("xTechLog", "Done writing: " );
        }
        catch (Exception e)
        {
            //Log.e("xTechLog", "File write failed: " + e.getMessage());
        }

    }



    @Override
    public boolean onStopCurrentWork() {
        Log.d("xTechLog","Thread id:"+Thread.currentThread().getId()+",onStopCurrentWork");
        return super.onStopCurrentWork();
    }
}
