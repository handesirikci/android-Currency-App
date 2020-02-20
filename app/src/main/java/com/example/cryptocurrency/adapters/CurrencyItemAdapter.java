package com.example.cryptocurrency.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.objects.CurrencyItem;

import java.util.List;

public class CurrencyItemAdapter extends RecyclerView.Adapter<CurrencyItemAdapter.MyViewHolder> {

    public List<CurrencyItem> currencyList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtValue;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtValue = (TextView) view.findViewById(R.id.txtValue);
        }
    }


    public CurrencyItemAdapter(List<CurrencyItem> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CurrencyItem currency_item = currencyList.get(position);
        holder.txtName.setText(currency_item.name);
        holder.txtValue.setText(String.valueOf(currency_item.value));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }
}