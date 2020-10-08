package com.wutsi.stats;

import com.opencsv.bean.CsvBindByName;

public class Track {
    @CsvBindByName(required = true)
    private String time;
    @CsvBindByName(column = "deviceid")
    private String deviceId;
    @CsvBindByName(column = "userid")
    private String userId;
    @CsvBindByName
    private String page;
    @CsvBindByName
    private String event;
    @CsvBindByName(column = "productid")
    private String productId;
    @CsvBindByName
    private String value;
    @CsvBindByName
    private String os;
    @CsvBindByName(column = "osversion")
    private String osVersion;
    @CsvBindByName(column = "devicetype")
    private String deviceType;
    @CsvBindByName
    private String browser;
    @CsvBindByName
    private String ip;
    @CsvBindByName(column = "long")
    private String longitude;
    @CsvBindByName(column = "lat")
    private String latitude;
    @CsvBindByName
    private String traffic;
    @CsvBindByName
    private String referer;
    @CsvBindByName
    private boolean bot;
    @CsvBindByName
    private String ua;

    @CsvBindByName(column = "hitid")
    private String hitId;

    @CsvBindByName(column = "source")
    private String source;

    @CsvBindByName(column = "medium")
    private String medium;

    @CsvBindByName(column = "campaign")
    private String campaign;

    @CsvBindByName(column = "url")
    private String url;

    public long getTimeToLong(){
        return Long.parseLong(this.getTime());
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public boolean getBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public boolean isBot() {
        return bot;
    }

    public String getHitId() {
        return hitId;
    }

    public void setHitId(String hitId) {
        this.hitId = hitId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(final String medium) {
        this.medium = medium;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(final String campaign) {
        this.campaign = campaign;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
