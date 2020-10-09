package com.wutsi.stats.hit;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Hit {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "time")
    private long time;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "hitid")
    private String hitId;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "storyId")
    private String storyId;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "deviceid")
    private String deviceId;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "userid")
    private String userId;

    public Hit(final long time, final String hitId, final String storyId, final String deviceId, final String userId) {
        this.time = time;
        this.storyId = storyId;
        this.hitId = hitId;
        this.deviceId = deviceId;
        this.userId = userId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public String getHitId() {
        return hitId;
    }

    public void setHitId(final String hitId) {
        this.hitId = hitId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(final String storyId) {
        this.storyId = storyId;
    }
}
