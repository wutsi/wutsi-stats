package com.wutsi.stats.device;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Device {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "deviceid")
    private String deviceId;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "userid")
    private String userId;

    public Device(final String deviceId, final String userId) {
        this.deviceId = deviceId;
        this.userId = userId;
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
}
