package com.example.cryptocurrency.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cryptocurrency.R;

public class ShowCurrencyActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_currency);

        Intent intent = getIntent();

        String user_name = intent.getStringExtra("currency_name");
        Double user_rating = intent.getDoubleExtra("currency_value" , 0);

        TextView txtViewName = findViewById(R.id.showName);
        TextView txtViewValue =  findViewById(R.id.showValue);

        if(!user_name.isEmpty()) {
            txtViewName.setText(user_name);
        }

        txtViewValue.setText(String.valueOf(user_rating));
    }
}
