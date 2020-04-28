package com.example.jobproject;

import java.util.ArrayList;

public class Details {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private ArrayList<DataValue> data = new ArrayList<>();
    private CompanyData ad;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<DataValue> getData() {
        return data;
    }

    public CompanyData getAd() {
        return ad;
    }
}
