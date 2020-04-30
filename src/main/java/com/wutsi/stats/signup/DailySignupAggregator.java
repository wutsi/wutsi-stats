package com.wutsi.stats.signup;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.Aggregator;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DailySignupAggregator implements Aggregator {
    private LocalDate date;

    public DailySignupAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        Map<String, Integer> counters = parse(iterator);
        List<Signup> data = counters.keySet()
                .stream()
                .map(it -> new Signup(date.toString(), it, counters.get(it)))
                .collect(Collectors.toList());

        new SignupWriter().write(data, output);
    }

    private  Map<String, Integer> parse(InputStreamIterator iterator) {
        InputStream in;
        Map<String, Integer> counters = new LinkedHashMap();
        while ((in = iterator.next()) != null){
            parse(in, counters);
        }
        return counters;
    }

    private void parse(InputStream in, Map<String, Integer> counters) {
        Reader reader = new InputStreamReader(in);
        CsvToBean<Track> csv = new CsvToBeanBuilder(reader)
                .withType(Track.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        List<Track> tracks = csv.parse();
        tracks.stream()
                .filter(it -> accept(it, fmt))
                .forEach( it -> increment(it, counters));
    }

    private void increment(Track track, Map<String, Integer> counters) {
        Integer count = counters.get(track.getUserId());
        if (count == null){
            counters.put(track.getUserId(), 1);
        } else {
            counters.put(track.getUserId(), count + 1);
        }
    }

    private boolean accept(Track track, DateFormat fmt) {
        return  "signup".equals(track.getEvent()) &&
                !track.getBot() &&
                fmt.format(getDate(track)).equals(date.toString());
    }

    private Date getDate(Track track) {
        return new Date(Long.parseLong(track.getTime()));
    }
}
