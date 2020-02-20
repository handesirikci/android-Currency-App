package com.example.cryptocurrency.objects;

public class CurrencyItem {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String name;
    public double value;

    public CurrencyItem(String name, double value)
    {
        this.name=name;
        this.value=value;
    }

}
