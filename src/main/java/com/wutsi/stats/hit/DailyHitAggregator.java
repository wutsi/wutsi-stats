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

public class DailyHitAggregator extends AbstractDailyAggregator<Hit> {
    public DailyHitAggregator(LocalDate date) {
        super(date);
    }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Hit> items = loadItems(iterator);
        new HitWriter().write(items, output);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, Hit> items) {
        String key = track.getHitId();
        Hit item = items.get(key);
        if (item == null){
            items.put(key, new Hit(track.getTimeToLong(), track.getHitId(), track.getDeviceId(), track.getUserId()));
        } else {
            String userId = track.getUserId();
            if (!isEmpty(userId) && isEmpty(item.getUserId())){
                item.setUserId(userId);
            }
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    @Override
    protected boolean isValidTrack(Track track) {
        return  "page.read".equalsIgnoreCase(track.getPage()) &&
                !isEmpty(track.getHitId()) &&
                !isEmpty(track.getDeviceId()) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }
}
