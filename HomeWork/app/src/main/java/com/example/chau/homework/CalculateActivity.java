package com.example.chau.homework;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CalculateActivity extends AppCompatActivity {

    Button btnCalculate;
    Spinner spinner1, spinner2;
    EditText editText1;//store value to transfer
    String firstCurrency, secondCurrency;//store name of 2 currency
    float firstRate, secondRate;//store rate of 2 currency
    TextView resultView;

    String xml = "";//storing XML content from Intent i

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        //Get UI Object
        resultView = (TextView) findViewById(R.id.result);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        spinner1 = (Spinner) findViewById(R.id.from);
        spinner2 = (Spinner) findViewById(R.id.to);
        editText1 = (EditText) findViewById(R.id.editText);

        //Set value for 2 spinners
        //Create Adapter
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_item);

        spinner1.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstCurrency = adapter.getItem(position).toString();
                //Toast.makeText(getApplicationContext(),firstCurrency, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secondCurrency = adapter.getItem(position).toString();
                //  Toast.makeText(getApplicationContext(),secondCurrency, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Get Intent
        Intent i = getIntent();
        //get String from intent i
        xml = i.getStringExtra("xml");

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float firstNum = 0.0f;
                    //get number to transfer
                    if(!editText1.getText().toString().equals(""))
                         firstNum = Float.parseFloat(editText1.getText().toString());

                    InputStream is = null;
                    try {
                        is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    //Create XML Builder
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        Document doc = db.parse(is);

                        Element root = doc.getDocumentElement();//lấy tag Root ra
                        NodeList list = root.getElementsByTagName("Exrate");// lấy toàn bộ node con của Root

                        float result = 0.0f;
                        if(!firstCurrency.equals("VND") && !secondCurrency.equals("VND")){
                            for (int i = 0; i < list.getLength(); i++) {// duyệt từ node đầu tiên cho tới node cuối cùng
                                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node

                                // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
                                if (node instanceof Element) {
                                    String temp = ((Element) node).getAttribute("CurrencyName");
                                    if (temp.equals(firstCurrency)) {
                                        String temp1 = ((Element) node).getAttribute("Buy");
                                        if(!temp1.equals(""))
                                            firstRate = Float.parseFloat(temp1);
                                    } else if (temp.equals(secondCurrency)) {
                                        String temp1 = ((Element) node).getAttribute("Buy");
                                        if(!temp1.equals(""))
                                            secondRate = Float.parseFloat(((Element) node).getAttribute("Buy"));
                                    }

                                }
                            }

                            result = (firstNum * firstRate) / secondRate;
                        }
                        else if(firstCurrency.equals("VND")){
                            // duyệt từ node đầu tiên cho tới node cuối cùng
                            for (int i = 0; i < list.getLength(); i++) {

                                // mỗi lần duyệt thì lấy ra 1 node
                                Node node = list.item(i);

                                // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
                                if (node instanceof Element) {
                                    String temp = ((Element) node).getAttribute("CurrencyName");
                                    if (temp.equals(secondCurrency)) {
                                        String temp1 = ((Element) node).getAttribute("Buy");
                                        if(!temp1.equals(""))
                                            secondRate = Float.parseFloat(((Element) node).getAttribute("Buy"));
                                    }

                                }
                            }
                            result = firstNum/ secondRate;
                        }
                        else if(secondCurrency.equals("VND")){
                            for (int i = 0; i < list.getLength(); i++) {

                                // mỗi lần duyệt thì lấy ra 1 node
                                Node node = list.item(i);

                                // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
                                if (node instanceof Element) {
                                    String temp = ((Element) node).getAttribute("CurrencyName");
                                    if (temp.equals(firstCurrency)) {
                                        String temp1 = ((Element) node).getAttribute("Buy");
                                        if(!temp1.equals(""))
                                            firstRate = Float.parseFloat(((Element) node).getAttribute("Buy"));
                                    }

                                }
                            }
                            result = firstNum * firstRate;
                        }
                        resultView.setText(resultView.getText() + ":  " + result);

                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                        Log.d("Parse ", "Parse 1");
                    } catch (SAXException e) {
                        Log.d("Parse ", "Parse 2");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.d("Parse ", "IO Exception");
                        e.printStackTrace();
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Log.d("Number ", "Parse Number");
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Number", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
