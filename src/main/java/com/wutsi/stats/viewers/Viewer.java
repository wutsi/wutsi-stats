package com.wutsi.stats.viewers;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Viewer {
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "productid")
    private String productId;
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "time")
    private String date;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "count")
    private int view;

    public Viewer(String date, String productId) {
        this.date = date;
        this.productId = productId;
        this.view = 0;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return this.date + "," + this.getProductId() + "," + this.getView();
    }
}
