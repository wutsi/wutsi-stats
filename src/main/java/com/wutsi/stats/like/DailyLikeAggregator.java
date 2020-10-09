package com.wutsi.stats.like;

import com.wutsi.stats.Track;
import com.wutsi.stats.hit.AbstractDailyHitAggregator;

import java.time.LocalDate;

public class DailyLikeAggregator extends AbstractDailyHitAggregator {
    public DailyLikeAggregator(LocalDate date) {
        super(date);
    }

    @Override
    protected boolean isValidEvent(final Track track) {
        final String event = track.getEvent();

        return event != null && (event.startsWith("like-") || "like".equals(event));
    }
}
