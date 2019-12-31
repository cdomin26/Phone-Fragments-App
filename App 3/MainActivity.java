/*
 * Christian Dominguez
 * CS 478 - Fall 2019
 * cdomin26@uic.edu
 * Project 3
 * 3 Apps: A1 & A2 - Broadcast Receivers, A3 - Fragments
 */

package com.example.cs478project3_a3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements PhoneNamesFragment.ListSelectionListener {

    private static final String APP_PERMISSION =  "edu.uic.cs478.s19.kaboom";
    private static final String BROADCAST_INTENT = "edu.uic.cs478.broadcast.intent";

    protected static ArrayList<String> phoneNamesArray;
    protected static ArrayList<Integer> phoneImagesArray;

    int shownIndex = -1;
    static String  OLD_ITEM = "old_item";
    private final PhoneImagesFragment mImagesFragment = new PhoneImagesFragment();
    private PhoneNamesFragment mNamesFragment = null;

    private int flag = 0;
    private int picID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(" " + "CS478Project3-A3");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_name);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        phoneImagesArray = new ArrayList<Integer>(
                Arrays.asList(R.drawable.iphoneelevenpromax,
                        R.drawable.iphone10s, R.drawable.googlepixel3,
                        R.drawable.oneplus7pro, R.drawable.razer2, R.drawable.samsunggalaxysten));

        phoneNamesArray = new ArrayList<String>(
                Arrays.asList(getString(R.string.iPhone11ProMaxInfo), getString(R.string.iPhoneXSInfo),
                        getString(R.string.GooglePixel3Info), getString(R.string.OnePlus7ProInfo), getString(R.string.Razer2Info),
                        getString(R.string.SamsungGalaxys10Info)));

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        mNamesFragment = new PhoneNamesFragment();
        shownIndex = -1;

        ft.replace(R.id.names_frame, mNamesFragment);
        ft.replace(R.id.images_frame, mImagesFragment);
        ft.addToBackStack(null);
        ft.commit();

        if(savedInstanceState != null){
            shownIndex = savedInstanceState.getInt(OLD_ITEM);
        }


    }//End of onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
       getMenuInflater().inflate(R.menu.menu_main, menu);
       return true;
    }//End of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int i = item.getItemId();

        if(i == R.id.launchA1andA2){
            Intent openApps = new Intent(BROADCAST_INTENT);

            if(flag == 1){
                openApps.putExtra("pic", picID);
            }

            sendOrderedBroadcast(openApps, APP_PERMISSION);
        }

        if(i == R.id.exitA3){
            finish();
        }

        return true;
    }//End of onOptionsItemSelected()

    public void onStart(){
        super.onStart();

        if(shownIndex >= 0){
            mImagesFragment.showImageAtIndex(shownIndex);
            mNamesFragment.setSelection(shownIndex);
            mNamesFragment.getListView().setItemChecked(shownIndex, true);
        }

    }//End of onStart()

    @Override
    public void onListSelection(int index){

        if(shownIndex != index){
            flag = 1;
            mImagesFragment.showImageAtIndex(index);
            shownIndex = index;
            picID = index;
        }

    }//End of onListSelection()

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        if(shownIndex >=0){
            outState.putInt(OLD_ITEM, shownIndex);
        }
        else{
            outState.putInt(OLD_ITEM, -1);
        }

    }//End of onSaveInstanceState()

}//End of MainActivity class
