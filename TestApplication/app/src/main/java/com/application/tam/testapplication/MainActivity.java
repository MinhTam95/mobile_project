package com.application.tam.testapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int length = Toast.LENGTH_SHORT;
    Context context = MainActivity.this;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(context,"onCreate Main",length).show();
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,MainActivity2.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(context,"onStart Main",length).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(context,"onStop Main",length).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(context,"onDestroy Main",length).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(context,"onPause Main",length).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(context,"onResume Main",length).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(context,"onRestart Main",length).show();
    }
}
