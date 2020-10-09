package com.wutsi.stats.hit;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.Track;
import com.wutsi.stats.impl.AbstractDailyAggregator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class AbstractDailyHitAggregator extends AbstractDailyAggregator<Hit> {
    public AbstractDailyHitAggregator(LocalDate date) {
        super(date);
    }

    abstract protected boolean isValidEvent(final Track track);

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Hit> items = loadItems(iterator);
        new HitWriter().write(items, output);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, Hit> items) {
        String key = track.getHitId();
        Hit item = items.get(key);
        if (item == null){
            items.put(key, new Hit(date.toString(), track.getHitId(), 1));
        } else {
            item.setCount(item.getCount()+1);
        }
    }

    @Override
    protected boolean isValidTrack(Track track) {
        return  "page.read".equalsIgnoreCase(track.getPage()) &&
                isValidEvent(track) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }
}
