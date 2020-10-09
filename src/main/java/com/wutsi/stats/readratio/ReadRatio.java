package com.wutsi.stats.readratio;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class ReadRatio {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "time")
    private String date;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "hitid")
    private String hitId;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "readtime")
    private long readTime;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "readratio")
    private int readRatio;

    public ReadRatio(final String date, final String hitId, final long readTime, final int readRatio) {
        this.date = date;
        this.hitId = hitId;
        this.readTime = readTime;
        this.readRatio = readRatio;
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

    public int getReadRatio() {
        return readRatio;
    }

    public void setReadRatio(final int readRatio) {
        this.readRatio = readRatio;
    }

    public long getReadTime() {
        return readTime;
    }

    public void setReadTime(final long readTime) {
        this.readTime = readTime;
    }
}
