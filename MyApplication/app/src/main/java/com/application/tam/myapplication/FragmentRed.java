package com.application.tam.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Chau on 05/06/2017.
 */

public class FragmentRed extends Fragment implements FragmentCallbacks{

    MainActivity main;

    TextView txtRed;
    Button btnRedClock;

    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate res/layout_red.xml which includes a textview and a button
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(
                R.layout.fragment_red, null);
        // plumbing - get a reference to widgets in the inflated layout
        txtRed = (TextView) view_layout_red.findViewById(R.id.textView1Red);
        // show string argument supplied by constructor (if any!)
        try {
            Bundle arguments = getArguments();
            String redMessage = arguments.getString("arg1", "");
            txtRed.setText(redMessage);
        } catch (Exception e) {
            Log.e("RED BUNDLE ERROR - ", "" + e.getMessage());
        }
        // clicking the button changes the time displayed and sends a copy to MainActivity
        btnRedClock = (Button) view_layout_red.findViewById(R.id.button1Red);
        btnRedClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redMessage = "Red clock:\n" + new Date().toString();
                txtRed.setText(redMessage);
                main.onMsgFromFragToMain("RED-FRAG", redMessage);
            }
        });
        return view_layout_red;
    }
    @Override
    public void onMsgFromMainToFragment(String strValue) {
    // receiving a message from MainActivity (it may happen at any point in time)
        txtRed.setText("THIS MESSAGE COMES FROM MAIN:" + strValue);
    }
}
