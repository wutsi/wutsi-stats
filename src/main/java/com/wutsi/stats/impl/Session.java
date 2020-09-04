package com.wutsi.stats.impl;

import com.wutsi.stats.Track;

public class Session {
    private String hitId;
    private String productId;
    private long startTimeMillis = 0;
    private long endTimeMillis = 0;

    public Session(String hitId, String productId) {
        this.hitId = hitId;
        this.productId = productId;
    }

    public long getDurationInSecond(){
        return (endTimeMillis-startTimeMillis)/1000;
    }

    private boolean accept(Track track) {
        String event = track.getEvent();
        return "readstart".equals(event) || "scroll".equals(event)  || "g_one_tap_show".equals(event);
    }

    public void add(Track track) {
        if (accept(track)){
            long time = track.getTimeToLong();
            if (endTimeMillis == 0 || startTimeMillis == 0){
                startTimeMillis = endTimeMillis = time;
            } else if (time < startTimeMillis){
                startTimeMillis = time;
            } else if (time > endTimeMillis) {
                endTimeMillis = time;
            }
        }
    }

    public String getHitId() {
        return hitId;
    }

    public void setHitId(String hitId) {
        this.hitId = hitId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
