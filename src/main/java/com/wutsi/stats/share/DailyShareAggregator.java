package com.wutsi.stats.share;

import com.wutsi.stats.Track;
import com.wutsi.stats.hit.AbstractDailyHitAggregator;

import java.time.LocalDate;

public class DailyShareAggregator extends AbstractDailyHitAggregator {
    public DailyShareAggregator(LocalDate date) {
        super(date);
    }

    @Override
    protected boolean isValidEvent(final Track track) {
        final String event = track.getEvent();
        final String value = track.getValue();

        return event != null &&
                event.startsWith("share-") ||
                ("share".equals(event) && value != null && !value.isEmpty());
    }
}
