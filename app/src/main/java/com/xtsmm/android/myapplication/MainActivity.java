package com.xtsmm.android.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    private Button btnStart,btnStop;
    JobScheduler jobScheduler;
    Intent serviceIntent;
    int count=0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        serviceIntent=new Intent(getApplicationContext(),MyJobIntentService.class);
        MyJobIntentService.enqueueWork(MainActivity.this,serviceIntent);

    }

}