package com.wutsi.stats.viewers;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.InputStreamIterator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

public class DailyViewersAggregator implements Aggregator {
    private LocalDate date;

    public DailyViewersAggregator (LocalDate date) {
        this.date = date;
    }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException {
        output.write("Hello world".getBytes());
    }
}
