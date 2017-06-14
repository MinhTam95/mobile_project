package com.application.tam.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainCallbacks{
    FragmentTransaction ft;
    FragmentRed red;
    FragmentBlue blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        red = FragmentRed.newInstance("red");
        ft.replace(R.id.main_holder_red,red);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        blue = FragmentBlue.newInstance("blue");
        ft.replace(R.id.main_holder_blue,blue);
        ft.commit();
    }


    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        // show message arriving to MainActivity
        Toast.makeText(getApplication(),
                " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG)
                .show();
        if (sender.equals("RED-FRAG")) {
            // TODO: if needed, do here something on behalf of the RED fragment
        }
        if (sender.equals("BLUE-FRAG")) {
            try {
                // forward blue-data to redFragment using its callback method
                red.onMsgFromMainToFragment("\nSender: " + sender
                        + "\nMsg: " + strValue);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}
