package com.wutsi.stats.readers;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractDailyAggregator;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DailyReadersAggregator extends AbstractDailyAggregator<Reader>{
    private LocalDate date;

    public DailyReadersAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Reader> readers = this.getOutputData(this.getTracks(iterator));
        ReaderWriter writer = new ReaderWriter();
        writer.write(readers, output);
    }

    protected boolean isValidTrack(Track track) {
        return  "scroll".equals(track.getEvent()) &&
                "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                this.isDate(track.getTime(), this.date);
    }

    protected List<Reader> getOutputData(List<Track> tracks) {
        List<Reader> outputData = new ArrayList<>();
        List<String> collectProductIdUse = new ArrayList<>();

        for (Track track : tracks) {
            String productId = track.getProductId();
            String deviceId = track.getDeviceId();
            if (!this.isReadFinished(tracks, deviceId, productId) && !collectProductIdUse.contains(productId)) {
                Reader tmp = this.createOutputData(track);
                tmp.setCount(this.countItemList(tracks, track.getProductId()));
                tmp.setDuration(this.computeDuration(tracks, deviceId, productId));
                outputData.add(tmp);
                collectProductIdUse.add(productId);
            }
        }

        Collections.sort(outputData, (track1, track2) -> {
            return (int) (track2.getDuration() - track1.getDuration());
        });
        return outputData;
    }

    @Override
    protected Reader createOutputData(Track track) {
        Reader reader = new Reader(this.date.toString(), track.getProductId());
        return reader;
    }

    private int computeDuration(List<Track> tracks, String deviceId, String productId) {
        List<Track> results = tracks.stream().filter(
                track -> productId.equals(track.getProductId()) &&
                        deviceId.equals(track.getDeviceId())
        ).collect(Collectors.toList());

        if(results.size() < 2) {
            return 0;
        }
        long milliSecondes = Long.parseLong(
            results.get(results.size()-1).getTime()) - Long.parseLong(results.get(0).getTime()
        );

        return (int) (milliSecondes * 0.001);
    }

    private boolean isReadFinished (List<Track> tracks, String deviceId, String productId){
        List<Track> results = tracks.stream().filter(
                track -> productId.equals(track.getProductId()) &&
                        deviceId.equals(track.getDeviceId()) &&
                        "100".equals(track.getValue())
        ).collect(Collectors.toList());

        return results.size() <= 0;
    }
}
