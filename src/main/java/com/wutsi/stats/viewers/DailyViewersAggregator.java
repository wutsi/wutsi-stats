package com.wutsi.stats.viewers;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractDailyAggregator;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DailyViewersAggregator extends AbstractDailyAggregator<Viewer> {
    private LocalDate date;

    public DailyViewersAggregator (LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Viewer> viewers = this.getOutputData(this.getTracks(iterator));
        ViewerWriter writer = new ViewerWriter();
        writer.write(viewers, output);
    }

    protected boolean isValidTrack(Track track) {
        return  "readstart".equals(track.getEvent()) &&
                "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                this.isDate(track.getTime(), this.date);
    }

    protected List<Viewer> getOutputData(List<Track> tracks) {
        List<Viewer> outputData = new ArrayList<>();
        List<String> collectProductIdUse = new ArrayList<>();

        for (Track track : tracks) {
            String productId = track.getProductId();

            if (!collectProductIdUse.contains(productId)) {
                Viewer tmp = this.createOutputData(track);
                tmp.setCount(this.countItemList(tracks, track.getProductId()));
                outputData.add(tmp);
                collectProductIdUse.add(productId);
            }
        }
        return outputData;
    }

    @Override
    protected Viewer createOutputData(Track track) {
        Viewer viewer = new Viewer(date.toString(), track.getProductId());
        return viewer;
    }
}
