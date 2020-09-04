package com.wutsi.stats.device;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.Track;
import com.wutsi.stats.impl.AbstractDailyAggregator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DailyDeviceAggregator extends AbstractDailyAggregator<Device> {
    public DailyDeviceAggregator(LocalDate date) {
        super(date);
    }

    @Override
    public void aggregate(final InputStreamIterator iterator, final OutputStream output) throws IOException, CsvException {
        List<Device> devices = loadItems(iterator);
        new DeviceWriter().write(devices, output);
    }

    @Override
    protected void handleTrack(final Track track, final Map<String, Device> devices) {
        String key = key(track);
        if (!devices.containsKey(key)){
            devices.put(key, new Device(track.getDeviceId(), track.getUserId()));
        }
    }

    @Override
    protected boolean isValidTrack(final Track track) {
        return !track.isBot() &&
                isValidDate(track.getTime(), date) &&
                (track.getUserId() != null && !track.getUserId().isEmpty()) &&
                (track.getDeviceId() != null && !track.getDeviceId().isEmpty())
        ;
    }

    private String key(Track track) {
        return track.getDeviceId() + "-" + track.getUserId();
    }
}
