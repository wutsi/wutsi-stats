package com.wutsi.stats.comment;

import com.wutsi.stats.Track;
import com.wutsi.stats.hit.AbstractDailyHitAggregator;

import java.time.LocalDate;

public class DailyCommentAggregator extends AbstractDailyHitAggregator {
    public DailyCommentAggregator(LocalDate date) {
        super(date);
    }

    @Override
    protected boolean isValidEvent(final Track track) {
        final String event = track.getEvent();

        return event != null && (event.startsWith("comment-") || "comment".equals(event));
    }
}
