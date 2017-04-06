package com.example.chau.homework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ShowInfoActivity extends Activity {
    TableLayout tl;
    TableRow tr;
    TextView code,name,buy,transfer,sell;
    TextView curTime;

    ArrayList<Info> data = null;
    String currentTime = "";
    String xml;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        //Get UI Objects
        tl = (TableLayout) findViewById(R.id.maintable);
        curTime = (TextView) findViewById(R.id.time);

        //Add Header to the table
        addHeaders();

        //Get the intent
        Intent i = getIntent();

        //Storing data retrieve by Intent i to String s variable
        //get String which is stored in MainActivity
        //Toast.makeText(this,i.getStringExtra("xml"), Toast.LENGTH_LONG).show();

        xml = i.getStringExtra("xml");
        //From String s.  We convert it to ArrayList of Info Objects
        data = getDataFromServer(xml);
        addData();
    }

    /** This function add the headers to the table **/
    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));


        /** Creating a TextView for Currency Name header **/
        TextView name = new TextView(this);
        name.setText("NAME");
        name.setTextColor(Color.GRAY);
        name.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
        name.setPadding(5, 5, 0, 0);
        name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(name); // Adding textView to tablerow.

        /** Creating a TextView for Buy header **/
        TextView buyPrice = new TextView(this);
        buyPrice.setText("BUY");
        buyPrice.setTextColor(Color.GRAY);
        buyPrice.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        buyPrice.setPadding(5, 5, 0, 0);
        buyPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(buyPrice); // Adding textView to tablerow.

        /** Creating a TextView for Transfer header **/
        TextView transferPrice = new TextView(this);
        transferPrice.setText("TRANSFER");
        transferPrice.setTextColor(Color.GRAY);
        transferPrice.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        transferPrice.setPadding(5, 5, 0, 0);
        transferPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(transferPrice); // Adding textView to tablerow.

        /** Creating a TextView for Sell header **/
        TextView sellPrice = new TextView(this);
        sellPrice.setText("SELL");
        sellPrice.setTextColor(Color.GRAY);
        sellPrice.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        sellPrice.setPadding(5, 5, 0, 0);
        sellPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(sellPrice); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

    }

    /** This function add the data to the table **/
    public void addData(){
        curTime.setText(currentTime);
        for (int i = 0; i < data.size(); i++)
        {
            Info temp = data.get(i);

            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

            name = new TextView(this);
            name.setText(temp.getName());
            name.setTextColor(Color.BLACK);
            name.setLayoutParams(new LayoutParams(100,LayoutParams.WRAP_CONTENT));
            name.setPadding(5, 5, 5, 5);
            name.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            tr.addView(name); // Adding textView to tablerow.

            buy = new TextView(this);
            buy.setText(temp.getBuyPrice().toString());
            buy.setTextColor(Color.BLACK);
            buy.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            buy.setPadding(5, 5, 5, 5);
            buy.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            tr.addView(buy); // Adding textView to tablerow.

            transfer = new TextView(this);
            transfer.setText(temp.getTransferPrice().toString());
            transfer.setTextColor(Color.BLACK);
            transfer.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            transfer.setPadding(5, 5, 5, 5);
            transfer.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            tr.addView(transfer); // Adding textView to tablerow.

            sell = new TextView(this);
            sell.setText(temp.getSellPrice().toString());
            sell.setTextColor(Color.BLACK);
            sell.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            sell.setPadding(5, 5, 5, 5);
            sell.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            tr.addView(sell); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        }
    }

    public ArrayList<Info> getDataFromServer(String dataFromServer){
        ArrayList<Info> result = new ArrayList<>();
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(dataFromServer.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Log.d("INSTREAM","Error Convert to InputStream");
            e.printStackTrace();
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element root = doc.getDocumentElement();//lấy tag Root ra
            NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root

            currentTime = root.getElementsByTagName("DateTime").item(0).getTextContent();

            for (int i = 1; i < list.getLength(); i++) {// duyệt từ node đầu tiên cho tới node cuối cùng
                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node
                // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
                if (node instanceof Element) {
                    Element type = (Element) node;// lấy được tag Employee ra

                    String code = type.getAttribute("CurrencyCode");//id là thuộc tính của tag Employee
                    String name = type.getAttribute("CurrencyName");//title là thuộc tính của tag type

                    float buyPrice = 0.00f;
                    float transferPrice = 0.00f;
                    float sellPrice = 0.00f;
                    String temp = type.getAttribute("Buy");
                    if(!temp.equals(""))
                        buyPrice = Float.parseFloat(temp);

                    temp = type.getAttribute("Transfer");
                    if(!temp.equals(""))
                        transferPrice = Float.parseFloat(temp);

                    temp = type.getAttribute("Sell");
                    if(!temp.equals(""))
                        sellPrice = Float.parseFloat(temp);

                    Info temp1 = new Info(code,name,buyPrice,transferPrice,sellPrice);

                    result.add(temp1);
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}