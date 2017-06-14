package com.application.tam.testapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    int length = Toast.LENGTH_SHORT;
    Context context = MainActivity2.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(context,"onCreate Main2",length).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(context,"onStart Main2",length).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(context,"onStop Main2",length).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(context,"onDestroy Main2",length).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(context,"onPause Main2",length).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(context,"onResume Main2",length).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(context,"onRestart Main2",length).show();
    }
}
