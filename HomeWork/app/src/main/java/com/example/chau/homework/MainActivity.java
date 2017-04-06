package com.example.chau.homework;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    Button showInfo;
    Button calculate;
    String xmlString ="";

    public void setData(String a){
        this.xmlString = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showInfo = (Button)findViewById(R.id.showInfo);
        calculate = (Button)findViewById(R.id.calculate);

        //Call Async Task
        //In Async Task Class, we will set data for XML variable
        new LoadDataXML(MainActivity.this).execute("https://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx");

        Log.d("XML",xmlString);
        //Create intent
        //Passing XML in String format to each destination Activity

        showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this,ShowInfoActivity.class);
                i1.putExtra("xml",xmlString);
                startActivity(i1);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this,CalculateActivity.class);
                i2.putExtra("xml",xmlString);
                startActivity(i2);
            }
        });
    }
}
