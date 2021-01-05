package com.wutsi.stats.comment;

import com.wutsi.stats.Track;
import com.wutsi.stats.hit.AbstractDailyHitSignalAggregator;

import java.time.LocalDate;

public class DailyCommentAggregator extends AbstractDailyHitSignalAggregator {
    public DailyCommentAggregator(LocalDate date) {
        super(date);
    }

    @Override
    protected boolean isValidEvent(final Track track) {
        return "comment".equals(track.getEvent());
    }
}
