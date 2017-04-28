package com.example.crocodile.monan;

/**
 * Created by CROCODILE on 4/13/2017.
 */

public class Zone {
    Integer ZoneID;
    String ZoneName;
    Integer Using;
    Integer ZCount;

    public Zone(Integer zoneID, String zoneName, Integer using, Integer ZCount) {
        this.ZoneID = zoneID;
        this.ZoneName = zoneName;
        this.Using = using;
        this.ZCount = ZCount;
    }

    public Integer getZoneID() {
        return ZoneID;
    }

    public void setZoneID(Integer zoneID) {
        ZoneID = zoneID;
    }

    public String getZoneName() {
        return ZoneName;
    }

    public void setZoneName(String zoneName) {
        ZoneName = zoneName;
    }

    public Integer getUsing() {
        return Using;
    }

    public void setUsing(Integer using) {
        Using = using;
    }

    public Integer getZCount() {
        return ZCount;
    }

    public void setZCount(Integer ZCount) {
        this.ZCount = ZCount;
    }
}
