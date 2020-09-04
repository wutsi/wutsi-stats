package com.wutsi.stats.impl;

import com.wutsi.stats.Track;

import java.util.HashMap;
import java.util.Map;

public class TrackCsvMapper {
    private Map<String, Integer> columnIndex = new HashMap<>();

    public void column(String[] col) {
        for (int i=0 ; i<col.length ; i++){
            columnIndex.put(col[i].toLowerCase(), i);
        }
    }

    public Track map(String[] col) {
        Track track = new Track();

        track.setHitId(getValue("hitid", col));
        track.setDeviceId(getValue("deviceid", col));
        track.setUserId(getValue("userid", col));
        track.setTime(getValue("time", col));
        track.setPage(getValue("page", col));
        track.setEvent(getValue("event", col));
        track.setProductId(getValue("productid", col));
        track.setValue(getValue("value", col));
        track.setPage(getValue("page", col));
        track.setOs(getValue("os", col));
        track.setOsVersion(getValue("osversion", col));
        track.setDeviceType(getValue("devicetype", col));
        track.setBrowser(getValue("browser", col));
        track.setIp(getValue("ip", col));
        track.setLongitude(getValue("long", col));
        track.setLatitude(getValue("lat", col));
        track.setTraffic(getValue("traffic", col));
        track.setReferer(getValue("referer", col));
        track.setUa(getValue("ua", col));
        track.setBot("true".equals(getValue("bot", col)));

        return track;
    }

    private String getValue(String column, String[] col) {
        Integer index = columnIndex.get(column.toLowerCase());
        if (index == null){
            return null;
        }

        return index < col.length ? col[index] : null;
    }
}
