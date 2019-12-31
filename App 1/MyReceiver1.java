package com.example.cs478project3_a1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1){

        PackageManager pm = arg0.getPackageManager();
        Intent li = pm.getLaunchIntentForPackage("com.example.cs478project3_a1");

        if(arg1.hasExtra("pic")) {
            int picID = li.getIntExtra("pic", 0);
            li.putExtra("yee", picID);
        }

        arg0.startActivity(li);

        //Catches broadcast intents from A3 and launches 2nd activity(website or photo)
         //The broadcast intent should have an extra that specifies the smart phone

    }//End of onReceive()

}//End of MyReceiver1 class
