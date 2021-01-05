package com.wutsi.stats.follower;

import com.wutsi.stats.Track;
import com.wutsi.stats.hit.AbstractDailyHitSignalAggregator;

import java.time.LocalDate;

public class DailyFollowerAggregator extends AbstractDailyHitSignalAggregator {
    public DailyFollowerAggregator(LocalDate date) {
        super(date);
    }

    @Override
    protected boolean isValidEvent(final Track track) {
        return "follow".equals(track.getEvent());
    }}
