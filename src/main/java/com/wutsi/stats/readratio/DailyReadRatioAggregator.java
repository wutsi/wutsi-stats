package com.wutsi.stats.readratio;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractDailySessionAggregator;
import com.wutsi.stats.impl.Session;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DailyReadRatioAggregator extends AbstractDailySessionAggregator {
    public DailyReadRatioAggregator(LocalDate date) {
        super(date);
    }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Session> sessions = loadItems(iterator);
        List<ReadRatio> items = sessions.stream()
                .map(it -> new ReadRatio(
                        this.date.toString(),
                        it.getHitId(),
                        it.getDurationInSecond(),
                        it.getMaxScroll()
                ))
                .collect(Collectors.toList());
        new ReadRatioWriter().write(items, output);
    }

}
