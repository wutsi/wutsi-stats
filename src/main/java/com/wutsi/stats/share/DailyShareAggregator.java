package com.wutsi.stats.share;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.Track;
import com.wutsi.stats.impl.AbstractDailyAggregator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DailyShareAggregator extends AbstractDailyAggregator<Share> {
    public DailyShareAggregator(LocalDate date) {
        super(date);
    }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Share> shares = loadItems(iterator);
        new ShareWriter().write(shares, output);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, Share> shares) {
        String key = track.getHitId();
        Share share = shares.get(key);
        if (share == null){
            shares.put(key, new Share(date.toString(), track.getHitId(), 1));
        } else {
            share.setCount(share.getCount()+1);
        }
    }

    @Override
    protected boolean isValidTrack(Track track) {
        return  "page.read".equalsIgnoreCase(track.getPage()) &&
                track.getEvent() != null &&
                (
                        track.getEvent().startsWith("share-") ||
                        ("share".equals(track.getEvent()) && track.getValue() != null && !track.getValue().isEmpty())
                ) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }
}
