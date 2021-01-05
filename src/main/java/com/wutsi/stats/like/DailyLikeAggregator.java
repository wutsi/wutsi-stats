package com.wutsi.stats.like;

import com.wutsi.stats.Track;
import com.wutsi.stats.hit.AbstractDailyHitSignalAggregator;

import java.time.LocalDate;

public class DailyLikeAggregator extends AbstractDailyHitSignalAggregator {
    public DailyLikeAggregator(LocalDate date) {
        super(date);
    }

    @Override
    protected boolean isValidEvent(final Track track) {
        return "like".equals(track.getEvent());
    }
}
