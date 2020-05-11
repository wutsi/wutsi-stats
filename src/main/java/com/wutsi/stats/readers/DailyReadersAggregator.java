package com.wutsi.stats.readers;

import com.opencsv.exceptions.CsvException;
import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.AbstractSessionAggregator;
import com.wutsi.stats.impl.Session;
import com.wutsi.stats.impl.Track;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DailyReadersAggregator extends AbstractSessionAggregator<Reader> {
    private LocalDate date;

    public DailyReadersAggregator(LocalDate date) { this.date = date; }

    public void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException, CsvException {
        List<Session> sessions = this.getSessions(iterator);
        List<Reader> readers = toReaders(sessions);
        new ReaderWriter().write(readers, output);
    }

    @Override
    protected boolean isValidTrack(Track track) {
        return  "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                isDate(track.getTime(), this.date);
    }

    @Override
    protected boolean isValidSession(Session session) {
        return session.hasTrackWithEvent("scroll", "100");
    }

    private List<Reader> toReaders(List<Session> sessions) {
        return sessions.stream()
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
}
