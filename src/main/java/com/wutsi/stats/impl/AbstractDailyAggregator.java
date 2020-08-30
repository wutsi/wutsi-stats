package com.wutsi.stats.impl;

import com.wutsi.stats.Aggregator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public abstract class AbstractDailyAggregator implements Aggregator {
    protected LocalDate date;

    public AbstractDailyAggregator(final LocalDate date) {
        this.date = date;
    }

    protected boolean isValidDate(String trackDate, LocalDate currectDate) {
        String trackCurrentDate = convertTimeStampToDateString(Long.parseLong(trackDate));
        return currectDate.toString().equals(trackCurrentDate);
    }

    private String convertTimeStampToDateString (long timestamp) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date(timestamp).getTime());
    }
}
