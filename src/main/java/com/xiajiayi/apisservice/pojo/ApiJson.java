package com.xiajiayi.apisservice.pojo;

/**
 * @Author 夏佳怡
 * @Date 2023/7/30 9:46
 * @Version 1.0
 * Description:
 */
public class ApiJson {
    private String returnCode;
    private String returnMsg;
    private String buildingID;
    private String roomID;
    private Powertable[] powerTable;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Powertable[] getPowerTable() {
        return powerTable;
    }

    public void setPowerTable(Powertable[] powerTable) {
        this.powerTable = powerTable;
    }
}
