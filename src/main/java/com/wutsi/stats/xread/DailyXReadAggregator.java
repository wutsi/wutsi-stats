package com.wutsi.stats.xread;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.Track;
import com.wutsi.stats.impl.AbstractDailyAggregator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DailyXReadAggregator  extends AbstractDailyAggregator<XRead> {
    public DailyXReadAggregator(LocalDate date) {
        super(date);
    }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<XRead> xreads = loadItems(iterator);
        new XReadWriter().write(xreads, output);
    }

    @Override
    protected boolean isValidTrack(final Track track) {
        return  "page.read".equalsIgnoreCase(track.getPage()) &&
                "xread".equalsIgnoreCase(track.getEvent()) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, XRead> items) {
        String key = track.getProductId();
        XRead item = items.get(key);
        if (item == null){
            items.put(key, new XRead(date.toString(), track.getProductId(), 1));
        } else {
            item.setCount(item.getCount()+1);
        }
    }
}
