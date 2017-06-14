package com.application.tam.staticfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainCallbacks{
    FragmentBlue blue;
    FragmentRed red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //Called when a fragment is attached to the activity.
    //This is called after the attached fragment's onAttach and before the attached fragment's onCreate if the fragment has not yet had a previous call to onCreate.
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        // get a reference to each fragment attached to the GUI
        //check type of fragment pass in
        if(fragment.getClass() == FragmentBlue.class){
            blue = (FragmentBlue)fragment;
        }
        else if(fragment.getClass() == FragmentRed.class){
            red = (FragmentRed)fragment;
        }
    }

    @Override
    public void onMsgFromFragToMain(String sender, String value) {
        Toast.makeText(getApplication(), " MAIN GOT MSG >> " + sender
                + "\n" + value, Toast.LENGTH_LONG).show();

        if (sender.equals("RED-FRAG")){
            //TODO: do here something smart on behalf of BLUE fragment
        }
        if (sender.equals("BLUE-FRAG")) {
            red.onMsgFromActivity("\nSender: " + sender + "\nMsg: " + value);
        }
    }
}
