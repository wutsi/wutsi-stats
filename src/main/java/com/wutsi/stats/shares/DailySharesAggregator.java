package com.wutsi.stats.shares;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.Aggregator;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DailySharesAggregator implements Aggregator {
    private LocalDate date;

    public DailySharesAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Share> shares = this.getViewers(iterator);
        ShareWriter writer = new ShareWriter();
        writer.write(shares, output);
    }

    private int countItemList (List<Track> tracks, String productId){
        int count = 0;
        for (Track track : tracks) {
            if(productId != null && productId.equals(track.getProductId())){
                count += 1;
            }
        }
        return count;
    }

    private boolean isValidTrack(Track track) {
        return  "share".equals(track.getEvent()) &&
                "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                this.isDate(track.getTime());
    }

    private boolean isDate(String trackDate) {
        String trackCurrentDate = this.convertTimeStampToDateString(Long.parseLong(trackDate));
        return this.date.toString().equals(trackCurrentDate);
    }

    private String convertTimeStampToDateString (long timestamp) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date(timestamp).getTime());
    }

    private List<Share> getViewers(InputStreamIterator iterator) throws IOException {
        return getViewers(this.getTracks(iterator));
    }

    private List<Share> getViewers(List<Track> tracks) {
        List<Share> shares = new ArrayList<>();
        List<String> collectProductIdUse = new ArrayList<>();

        for (Track track : tracks) {
            String productId = track.getProductId();

            if (!collectProductIdUse.contains(productId)) {
                Share share = new Share(date.toString(), productId);
                share.setShare(this.countItemList(tracks, productId));
                shares.add(share);
                collectProductIdUse.add(productId);
            }
        }
        return shares;
    }

    private List<Track> getTracks(InputStreamIterator iterator) throws IOException {
        List<Track> tracks = new ArrayList<>();
        do {
            Reader reader = new InputStreamReader(iterator.next());
            try {
                CsvToBean<Track> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Track.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                List<Track> tracksTmp = csvToBean.parse().stream().filter(
                        track -> this.isValidTrack(track)
                ).collect(Collectors.toList());
                tracks.addAll(tracksTmp);
            } finally{
                reader.close();
            }
        } while(iterator.hasNext());
        return tracks;
    }
}
