package com.example.cs478project3_a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1){

        Toast.makeText(arg0, "Receiver in A2 activated!", Toast.LENGTH_LONG).show();

        PackageManager pm = arg0.getPackageManager();
        Intent li = pm.getLaunchIntentForPackage("com.example.cs478project3_a2");
        arg0.startActivity(li);

    }//End of onReceive()

}//End of MyReceiver2 class
