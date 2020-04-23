package com.wutsi.stats.shares;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.InputStreamIterator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

public class DailySharesAggregator implements Aggregator {
    private LocalDate date;

    public DailySharesAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException {
        output.write("Hello world".getBytes());
    }
}
