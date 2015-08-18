package com.example.sourav.twitterapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


public class MyPositionMap extends FragmentActivity {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_position_map);
//        initializeMap();
        getName();


    }


    private void initializeMap() {
        if (googleMap == null) {
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment)).getMap();

        }

        if (googleMap == null) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void getName() {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String storedName = sharedPreferences.getString("UserName", null);
        if (storedName == null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.entername, null);
            final EditText name = (EditText) v.findViewById(R.id.etname);


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String nameEntered =  name.getText().toString().trim();
                    if( nameEntered.length()>0) {
                        SharedPreferences sharedPref = MyPositionMap.this.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("UserName",nameEntered);
                        editor.commit();
                    }
                }
            });
            alertDialogBuilder.setView(v);
            alertDialogBuilder.show();
        }

    }

}