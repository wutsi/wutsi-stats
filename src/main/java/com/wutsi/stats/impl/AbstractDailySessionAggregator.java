package com.wutsi.stats.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wutsi.stats.InputStreamIterator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractDailySessionAggregator extends AbstractDailyAggregator {
    public AbstractDailySessionAggregator(LocalDate date){
        super(date);
    }

    protected abstract boolean isValidSession(Session session);

    protected List<Session> getSessions(InputStreamIterator iterator) throws IOException{
        Map<String, Session> sessions = new LinkedHashMap<>();
        do {
            Reader reader = new InputStreamReader(iterator.next());
            try {
                loadSessions(reader, sessions);
            } finally{
                reader.close();
            }
        } while(iterator.hasNext());

        return sessions.values().stream()
                .filter(it -> isValidSession(it))
                .collect(Collectors.toList());
    }

    private void loadSessions(Reader reader, Map<String, Session> sessions) throws IOException {
        try {
            CsvToBean<Track> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Track.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvToBean.parse().stream()
                    .filter(track -> this.isValidTrack(track))
                    .forEach(it -> addTrack(it, sessions) );

        } finally{
            reader.close();
        }
    }

    protected boolean isValidTrack(Track track) {
        return  "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }

    private void addTrack(Track track, Map<String, Session> sessions) {
        Session session = sessions.get(track.getHitId());
        if(session == null){
            session = new Session(track.getHitId(), track.getProductId());
            sessions.put(track.getHitId(), session);
        }
        session.add(track);
    }
}
