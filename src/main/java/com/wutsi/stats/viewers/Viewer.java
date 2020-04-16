package com.wutsi.stats.viewers;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Viewer {
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "productid")
    private int productId;
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "time")
    private String date;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "count")
    private int view;

    public Viewer(String date, int productId) {
        this.date = date;
        this.productId = productId;
        this.view = 0;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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
