package com.wutsi.stats.shares;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractDailyAggregator;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

public class DailySharesAggregator extends AbstractDailyAggregator<Share>{
    private LocalDate date;

    public DailySharesAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Share> shares = this.getOutputData(this.getTracks(iterator));
        ShareWriter writer = new ShareWriter();
        writer.write(shares, output);
    }

    protected boolean isValidTrack(Track track) {
        return  "share".equals(track.getEvent()) &&
                "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                this.isDate(track.getTime(), this.date);
    }

    @Override
    protected Share createOutputData(Track track) {
        Share share = new Share(this.date.toString(), track.getProductId());
        return share;
    }
}
