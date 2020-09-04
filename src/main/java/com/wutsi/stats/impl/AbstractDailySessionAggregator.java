package com.wutsi.stats.impl;

import com.wutsi.stats.Track;

import java.time.LocalDate;
import java.util.Map;

public abstract class AbstractDailySessionAggregator extends AbstractDailyAggregator<Session> {
    public AbstractDailySessionAggregator(LocalDate date){
        super(date);
    }

    @Override
    protected boolean isValidTrack(Track track) {
        return  "page.read".equals(track.getPage()) &&
                !track.getBot() &&
                isValidDate(track.getTime(), this.date);
    }

    protected void handleTrack(Track track, final Map<String, Session> sessions) {
        Session session = sessions.get(track.getHitId());
        if(session == null){
            session = new Session(track.getHitId(), track.getProductId());
            sessions.put(track.getHitId(), session);
        }
        session.add(track);
    }
}
