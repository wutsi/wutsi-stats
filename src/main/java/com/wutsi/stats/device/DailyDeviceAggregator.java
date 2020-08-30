package com.wutsi.stats.device;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractDailyAggregator;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DailyDeviceAggregator extends AbstractDailyAggregator {
    public DailyDeviceAggregator(LocalDate date) {
        super(date);
    }

    @Override
    public void aggregate(final InputStreamIterator iterator, final OutputStream output) throws IOException, CsvException {
        List<Device> devices = loadDevices(iterator);
        new DeviceWriter().write(devices, output);
    }

    private List<Device> loadDevices(final InputStreamIterator iterator) throws IOException {
        Map<String, Device> devices = new LinkedHashMap();
        do {
            Reader reader = new InputStreamReader(iterator.next());
            try {
                loadDevices(reader, devices);
            } finally{
                reader.close();
            }
        } while(iterator.hasNext());


        return new ArrayList(devices.values());
    }

    private void loadDevices(final Reader reader, final Map<String, Device> devices) {
        CsvToBean<Track> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Track.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        csvToBean.parse().stream()
                .filter(track -> isValidTrack(track))
                .forEach(it -> addTrack(it, devices) );
    }

    private void addTrack(final Track track, final Map<String, Device> devices) {
        String key = key(track);
        if (!devices.containsKey(key)){
            devices.put(key, new Device(track.getDeviceId(), track.getUserId()));
        }
    }

    private boolean isValidTrack(final Track track) {
        return !track.isBot() &&
                isValidDate(track.getTime(), date) &&
                (track.getUserId() != null && !track.getUserId().isEmpty())
        ;
    }

    private String key(Track track) {
        return track.getDeviceId() + "-" + track.getUserId();
    }
}
