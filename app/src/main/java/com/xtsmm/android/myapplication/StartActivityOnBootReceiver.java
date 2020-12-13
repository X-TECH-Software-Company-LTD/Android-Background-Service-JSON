package com.xtsmm.android.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartActivityOnBootReceiver extends BroadcastReceiver {
    Intent serviceIntent;
    int count=0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("xTechLog","on Boot");
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){

            /*Intent i=new Intent(context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);*/

            serviceIntent=new Intent(context,MyJobIntentService.class);
            serviceIntent.putExtra("starter","starter"+(++count));
            MyJobIntentService.enqueueWork(context,serviceIntent);

            /*Intent serviceIntent=new Intent(context,ExampleJobIntentService.class);
            serviceIntent.putExtra("inputExtra","zzzz");
            ExampleJobIntentService.enqueueWork(context,serviceIntent);*/

        }
    }

    public void enqueueWork(){

    }
}
