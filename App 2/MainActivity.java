/*
 * Christian Dominguez
 * CS 478 - Fall 2019
 * cdomin26@uic.edu
 * Project 3
 * 3 Apps: A1 & A2 - Broadcast Receivers, A3 - Fragments
 */

package com.example.cs478project3_a2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button b2;
    private IntentFilter iFilter;
    private BroadcastReceiver a2Receiver;
    private static final String APP_PERMISSION = "edu.uic.cs478.s19.kaboom";
    private static final String BROADCAST_INTENT = "edu.uic.cs478.broadcast.intent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b2 = (Button) findViewById(R.id.button);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast();
            }

        });//End of b2 click listener

        iFilter = new IntentFilter(BROADCAST_INTENT);
        iFilter.setPriority(100);
        a2Receiver = new MyReceiver2();
        registerReceiver(a2Receiver, iFilter);

    }//End of onCreate()

    protected void onResume(){
        super.onResume();

        iFilter = new IntentFilter(BROADCAST_INTENT);
        iFilter.setPriority(100);
        a2Receiver = new MyReceiver2();
        registerReceiver(a2Receiver, iFilter);

    }//End of onResume()

    protected void onPause(){
        super.onPause();
        unregisterReceiver(a2Receiver);
    }//End of onPause()

    private void checkPermissionAndBroadcast(){

        if(ContextCompat.checkSelfPermission(this, APP_PERMISSION)
                == PackageManager.PERMISSION_GRANTED){
            Intent i = new Intent();
            i.setComponent(new ComponentName("com.example.cs478project3_a3","com.example.cs478project3_a3.MainActivity"));
            startActivity(i);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{APP_PERMISSION}, 0);
        }

    }//End of checkPermissionAndBroadcast()

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results){

        if(results.length > 0){
            if(results[0] == PackageManager.PERMISSION_GRANTED){
                Intent i = new Intent();
                i.setComponent(new ComponentName("com.example.cs478project3_a3","com.example.cs478project3_a3.MainActivity"));
                startActivity(i);
            }
            else{
                Toast.makeText(this, "No permission. It be like that sometimes, though.", Toast.LENGTH_SHORT).show();
            }
        }

    }//End of onRequestPermissionsResult()

}//End of MainActivity class
