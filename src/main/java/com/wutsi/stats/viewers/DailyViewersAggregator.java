package com.wutsi.stats.viewers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.Aggregator;
import com.wutsi.stats.InputStreamIterator;

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

public class DailyViewersAggregator implements Aggregator {
    private LocalDate date;

    public DailyViewersAggregator (LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Viewer> viewers = this.getViewers(iterator);
        ViewerWriter writer = new ViewerWriter();
        writer.write(viewers, output);
    }

    private int countItemList (List<Track> tracks, int productId){
        int count = 0;
        for (Track track : tracks) {
            if(track.getProductId() == productId){
                count += 1;
            }
        }
        return count;
    }

    private boolean isValidTrack(Track track) {
        return  "readstart".equals(track.getEvent()) &&
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

    private List<Viewer> getViewers(InputStreamIterator iterator) throws IOException {
        return getViewers(this.getTracks(iterator));
    }

    private List<Viewer> getViewers(List<Track> tracks) {
        List<Viewer> viewers = new ArrayList<>();
        List<Integer> collectProductIdUse = new ArrayList<>();

        for (Track track : tracks) {
            int productId = track.getProductId();

            if (!collectProductIdUse.contains(productId)) {
                Viewer viewer = new Viewer(date.toString(), productId);
                viewer.setView(this.countItemList(tracks, productId));
                viewers.add(viewer);
                collectProductIdUse.add(productId);
            }
        }
        return viewers;
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
