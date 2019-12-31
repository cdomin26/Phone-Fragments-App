package com.example.cs478project3_a3;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.ListFragment;

public class PhoneNamesFragment extends ListFragment {

    private ListSelectionListener lsl = null;
    private static final String TAG = "PhoneNamesFragment";
    static final String OLD_POSITION = "oldPos" ;
    Integer mOldPosition = null;

    public interface ListSelectionListener{
        public void onListSelection(int index);
    }//End of ListSelectionListener interface

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id){
        //single or multiple? See onActivityCreated
        getListView().setItemChecked(pos, true);
        lsl.onListSelection(pos);

    }//End onListItemClick()

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);

        try{
            lsl = (ListSelectionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement OnListSelectionListener");
        }

    }//End of onAttach()

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }//End of onCreate()

    @Override
    public void onActivityCreated(Bundle savedState){

        super.onActivityCreated(savedState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.names, MainActivity.phoneNamesArray));

    }//End of onActivityCreated()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState){

        View retView = super.onCreateView(inflater, container, savedState);
        setRetainInstance(true);

        if(savedState != null){
            int oldPosition = savedState.getInt(OLD_POSITION);
            mOldPosition = oldPosition;
        }
        else{
            mOldPosition = null;
        }

        return retView;

    }//End of onCreateView()

    @Override
    public void onStart(){

        super.onStart();

        if(mOldPosition != null){
            int oldPosition = mOldPosition;
            getListView().setSelection(oldPosition);

            //Tell PhoneImagesActivity that this item was selected
            lsl.onListSelection(oldPosition);
        }

    }//End of onStart()

}//End of PhoneNamesFragment class
