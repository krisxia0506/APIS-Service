package com.xiajiayi.apisservice.mapper;

import com.xiajiayi.apisservice.pojo.Powertable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 夏佳怡
 * @Date 2023/7/28 21:17
 * @Version 1.0
 * Description:
 */
@Mapper
public interface PowerTableMapper {
    /**
     * 获取近6天的历史电量
     *
     * @param buildingID 楼栋ID
     * @param roomID     房间ID
     * @return 表对象表对象的列表
     */
    public List<Powertable> getHistoricalElectricity(String buildingID, String roomID);

    /**
     * 获取近7天的耗电量
     *
     * @param buildingID 楼栋ID
     * @param roomID     房间ID
     * @return 表对象的列表
     */
    public List<Powertable> getPowerConsumption(String buildingID, String roomID);

    public String getAvgPowerConsumption(String buildingID, String roomID);

}
