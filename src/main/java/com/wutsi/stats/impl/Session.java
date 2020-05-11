package com.wutsi.stats.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Session {
    private String hitId;
    private String productId;
    private List<Track> tracks = new ArrayList<>();

    public Session(String hitId, String productId) {
        this.hitId = hitId;
        this.productId = productId;
    }

    public boolean hasTrackWithEvent(String event) {
        for(Track track: tracks){
            if(event.equals(track.getEvent())){
                return true;
            }
        }
        return false;
    }

    public boolean hasTrackWithEvent(String event, String value) {
        for(Track track: tracks){
            if(event.equals(track.getEvent()) && value.equals(track.getValue())){
                return true;
            }
        }
        return false;
    }

    public long getDurationInSecond(){
        Collections.sort(
                this.tracks,
                (t1, t2) -> (int) (t1.getTimeToLong() - t2.getTimeToLong())
        );

        List<Track> tracksTmp = this.tracks.stream()
                .filter(track -> "scroll".equals(track.getEvent()))
                .collect(Collectors.toList());

        if(tracksTmp.size() < 2){
            return 0;
        }

        return (tracksTmp.get(tracksTmp.size() - 1).getTimeToLong() - tracksTmp.get(0).getTimeToLong()) / 1000;
    }

    public void add(Track track) {
        tracks.add(track);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
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
