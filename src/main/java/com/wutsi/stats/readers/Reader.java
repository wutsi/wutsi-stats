package com.wutsi.stats.readers;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.wutsi.stats.impl.OutputData;

public class Reader implements OutputData {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "time")
    private String date;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "productid")
    private String productId;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "count")
    private int count;
    @CsvBindByPosition(position = 3)
    private long duration;

    public Reader(String date, String productId, int count, long duration) {
        this.date = date;
        this.productId = productId;
        this.count = count;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }
}
