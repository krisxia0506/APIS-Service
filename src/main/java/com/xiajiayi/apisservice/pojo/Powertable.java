package com.xiajiayi.apisservice.pojo;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @TableName powertable
 */
public class Powertable implements Serializable {

    /**
     *
     */
    private Integer buildingID;
    /**
     *
     */
    private Integer roomID;
    /**
     *
     */
    private Double power;
    /**
     *
     */
    private Date date;

    public Integer getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Integer buildingID) {
        this.buildingID = buildingID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
