package com.example.cryptocurrency.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapters.CurrencyItemAdapter;
import com.example.cryptocurrency.objects.CurrencyItem;
import com.example.cryptocurrency.utils.RecyclerTouchListener;

import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private List<CurrencyItem> currencyList = new ArrayList<>(); //it holds currency items that will be given to adapter as a parameter
    private RecyclerView recyclerView; // in order to display a scrolling list of elements based on large or frequently changing data sets
    private CurrencyItemAdapter mAdapter;  //Adapters provide a binding from data set to views that are displayed within a RecyclerView
    private Map<String,Double> currencyMap; // maps every currency to its value
    private String data= "";


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        currencyMap = new HashMap<String, Double>();

        mAdapter = new CurrencyItemAdapter(currencyList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CurrencyItem currencyItem = currencyList.get(position);
                Toast.makeText(getApplicationContext(), currencyItem.name + " is selected!", Toast.LENGTH_SHORT).show();
                Log.d("selected value:", currencyItem.name + " is selected!");

                Intent intent = new Intent(MainActivity.this, ShowCurrencyActivity.class);
                intent.putExtra("currency_name", currencyItem.name);
                intent.putExtra("currency_value", currencyItem.value);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));




        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Create URL
                URL currencyEndpoint = null;
                try {
                    currencyEndpoint = new URL("https://api.exchangeratesapi.io/latest");// Create connection

                    HttpsURLConnection myConnection = (HttpsURLConnection) currencyEndpoint.openConnection();

                    InputStream input= myConnection.getInputStream();


                    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(input));

                    String line="";

                    while(line!=null) {
                        if(!line.equals("null")) {
                            data += line;
                        }
                        line = bufferedReader.readLine();

                    }

                    Log.i("JSONDATA", data);

                    JSONObject obj = null;
                    try {
                        obj = new JSONObject(data);
                        JSONObject j = obj.getJSONObject("rates");
                        Iterator<String> keys = j.keys();
                        while (keys.hasNext()) {
                            String keyValue = (String) keys.next();
                            currencyMap.put(keyValue, (Double) j.getDouble(keyValue));
                        }
                        prepareCurrencyData();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void prepareCurrencyData() {


        Iterator<String> keys = currencyMap.keySet().iterator();
        while(keys.hasNext()) {
            String temp=keys.next();
            CurrencyItem item = new CurrencyItem((String) temp, (Double) currencyMap.get(temp));
            currencyList.add(item);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });


    }

}