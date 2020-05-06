package com.wutsi.stats.readers;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractDailyAggregator;
import com.wutsi.stats.impl.Session;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DailyReadersAggregator extends AbstractDailyAggregator<Reader>{
    private LocalDate date;

    public DailyReadersAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Session> sessions = this.getSessions(this.getTracks(iterator));
        List<Reader> readers = toReaders(sessions);

        ReaderWriter writer = new ReaderWriter();
        writer.write(readers, output);
    }

    protected boolean isValidTrack(Track track) {
        return  "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                this.isDate(track.getTime(), this.date);
    }

    @Override
    protected Reader createOutputData(Track track) {
        return null;
    }

    private List<Session> getSessions(List<Track> tracks) {
        Map<String, Session> hits = new LinkedHashMap<>();

        for(Track track: tracks){
            Session session = hits.get(track.getHitId());
            if(session == null){
                session = new Session(track.getHitId(), track.getProductId());
                hits.put(track.getHitId(), session);
            }
            session.add(track);
        }

        return new ArrayList<>(hits.values());
    }

    private List<Reader> toReaders(List<Session> sessions) {
        return sessions.stream()
                .filter(session -> this.isValidSession(session))
                .collect(groupingBy(Session::getProductId))
                .values()
                .stream()
                .map(it -> toReader(it))
                .collect(Collectors.toList());
    }

    private Reader toReader(List<Session> sessions) {
        long duration = sessions.stream()
                .mapToLong(Session::getDurationInSecond)
                .sum();

        return new Reader(this.date.toString(), sessions.get(0).getProductId(), sessions.size(), duration);
    }

    private boolean isValidSession(Session session) {
        for(Track track: session.getTracks()){
            if("scroll".equals(track.getEvent()) && "100".equals(track.getValue())){
                return true;
            }
        }
        return false;
    }

    protected List<Reader> getOutputData(List<Track> tracks) {
        List<Reader> outputData = new ArrayList<>();
        return outputData;
    }
}
