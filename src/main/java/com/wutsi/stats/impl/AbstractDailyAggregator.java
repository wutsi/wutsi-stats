package com.wutsi.stats.impl;

import com.opencsv.CSVReader;
import com.wutsi.stats.Aggregator;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.Track;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDailyAggregator<T> implements Aggregator {
    protected LocalDate date;

    public AbstractDailyAggregator(final LocalDate date) {
        this.date = date;
    }

    protected List<T> loadItems(final InputStreamIterator iterator) throws IOException {
        Map<String, T> items = new LinkedHashMap();
        do {
            Reader reader = new InputStreamReader(iterator.next());
            try {
                loadItems(reader, items);
            } finally{
                reader.close();
            }
        } while(iterator.hasNext());

        return new ArrayList(items.values());
    }

    protected void loadItems(final Reader reader, final Map<String, T> items) {
        final TrackCsvMapper mapper = new TrackCsvMapper();
        final CSVReader csv = new CSVReader(reader);
        final Iterator<String[]> iterator = csv.iterator();
        int row = 0;
        for ( ; iterator.hasNext() ; row++){
            if (row == 0) {
                mapper.column(iterator.next());
            } else {
                Track track = mapper.map(iterator.next());
                if (isValidTrack(track)){
                    handleTrack(track, items);
                }
            }
        }
    }

    protected abstract boolean isValidTrack(Track track);

    protected abstract void handleTrack(Track track, final Map<String, T> items);

    protected boolean isValidDate(String trackDate, LocalDate currectDate) {
        String trackCurrentDate = convertTimeStampToDateString(Long.parseLong(trackDate));
        return currectDate.toString().equals(trackCurrentDate);
    }

    private String convertTimeStampToDateString (long timestamp) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date(timestamp).getTime());
    }
}
