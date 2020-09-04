package com.wutsi.stats.viewers;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.Track;
import com.wutsi.stats.impl.AbstractDailyAggregator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DailyViewersAggregator extends AbstractDailyAggregator<Viewer> {
    public DailyViewersAggregator (LocalDate date) {
        super(date);
    }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Viewer> viewers = loadItems(iterator);
        new ViewerWriter().write(viewers, output);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, Viewer> viewers) {
        String key = track.getProductId();
        Viewer viewer = viewers.get(key);
        if (viewer == null){
            viewers.put(key, new Viewer(date.toString(), track.getProductId(), 1));
        } else {
            viewer.setCount(viewer.getCount()+1);
        }
    }

    @Override
    protected boolean isValidTrack(Track track) {
        return  "page.read".equalsIgnoreCase(track.getPage()) &&
                "readstart".equalsIgnoreCase(track.getEvent()) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }
}
