package com.wutsi.stats.share;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Share {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "time")
    private String date;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "hitid")
    private String hitId;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "count")
    private int count;

    public Share(String date, String hitId, int count) {
        this.date = date;
        this.hitId = hitId;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getHitId() {
        return hitId;
    }

    public void setHitId(final String hitId) {
        this.hitId = hitId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }
}
