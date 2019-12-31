package com.example.cs478project3_a3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class PhoneImagesFragment extends Fragment {

    private static final String TAG = "PhoneImagesFragment";
    private ImageView mImageView = null;
    private int currIndex = -1;
    private int imageArrayLen;

    void showImageAtIndex(int newIndex){

        if(newIndex < 0 || newIndex >= imageArrayLen){
            return;
        }

        currIndex = newIndex;
        mImageView.setImageResource(MainActivity.phoneImagesArray.get(currIndex));

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }//End of onAttach()

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }//End of onCreate()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        setRetainInstance(true);
        return inflater.inflate(R.layout.images, container, false);
    }//End of onCreateView()

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mImageView = (ImageView)getActivity().findViewById(R.id.imageView);
        imageArrayLen = MainActivity.phoneImagesArray.size();

//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(" " + "CS478Project3-A3");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setLogo(R.drawable.ic_action_name);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);

    }//End of onActivityCreated()

}//End of PhoneImagesFragment class
