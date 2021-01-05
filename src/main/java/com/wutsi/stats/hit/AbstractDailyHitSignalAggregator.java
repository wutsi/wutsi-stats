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

public abstract class AbstractDailyHitSignalAggregator extends AbstractDailyAggregator<HitSignal> {
    public AbstractDailyHitSignalAggregator(LocalDate date) {
        super(date);
    }

    protected abstract boolean isValidEvent(final Track track);

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<HitSignal> items = loadItems(iterator);
        new HitSignalWriter().write(items, output);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, HitSignal> items) {
        String key = track.getHitId();
        HitSignal item = items.get(key);
        if (item == null){
            items.put(key, new HitSignal(date.toString(), track.getHitId(), 1));
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
