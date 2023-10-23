package com.xiajiayi.apisservice.controller;

import com.xiajiayi.apisservice.mapper.PowerTableMapper;
import com.xiajiayi.apisservice.pojo.ApiJson;
import com.xiajiayi.apisservice.pojo.Powertable;
import com.xiajiayi.apisservice.pojo.WanXiaoJson;
import com.xiajiayi.apisservice.service.SimulateRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Author 夏佳怡
 * @Date 2023/7/28 16:15
 * @Version 1.0
 * Description: 用于处理电量相关的请求
 */
@Slf4j
@Controller
public class ElectricityLevelController {
    @Autowired
    SimulateRequestService simulateRequestService;
    @Autowired
    PowerTableMapper powerTableMapper;

    /**
     * 获取当前电量
     *
     * @param buildingID 楼栋ID
     * @param roomID     房间ID
     * @return 当前电量
     */
    @ResponseBody
    @GetMapping("/electricityLevel")
    public WanXiaoJson electricityLevel(String buildingID, String roomID) {
        WanXiaoJson s = simulateRequestService.sendRequestToQueryPowerLevel(buildingID, roomID);
        log.info("此次请求结束了----------------------------------------");
        if (s == null) {
            WanXiaoJson wanXiaoJson = new WanXiaoJson();
            wanXiaoJson.setReturncode("400");
            return wanXiaoJson;
        } else {
            return s;
        }
    }


    /**
     * 获取近6天的历史电量
     *
     * @param buildingID 楼栋ID
     * @param roomID     房间ID
     * @return json格式的电量列表，包含日期和电量
     */
    @ResponseBody
    @GetMapping("/getHistoricalElectricityToJson")
    public ApiJson getHistoricalElectricityToJson(String buildingID, String roomID) {
        List<Powertable> historicalElectricity = powerTableMapper.getHistoricalElectricity(buildingID, roomID);
        Powertable[] powerTableArray = historicalElectricity.toArray(new Powertable[0]);

        ApiJson apiJson = new ApiJson();
        if (historicalElectricity.isEmpty()) {
            apiJson.setReturnCode("400"); // 设置返回码
            apiJson.setReturnMsg("No-data"); // 设置返回信息
            apiJson.setBuildingID(buildingID); // 设置buildingID
            apiJson.setRoomID(roomID); // 设置roomID
            return apiJson;
        } else {
            apiJson.setReturnCode("200"); // 设置返回码
            apiJson.setReturnMsg("Success"); // 设置返回信息
            apiJson.setBuildingID(buildingID); // 设置buildingID
            apiJson.setRoomID(roomID); // 设置roomID
            apiJson.setPowerTable(powerTableArray); // 设置Powertable数组
        }
        return apiJson;
    }

    /**
     * 获取7天历史耗电量
     *
     * @param buildingID 楼栋ID
     * @param roomID     房间ID
     * @return json格式的电量列表，包含日期和电量
     */
    @ResponseBody
    @GetMapping("/getPowerConsumptionToJson")
    public ApiJson getPowerConsumption(String buildingID, String roomID) {
        List<Powertable> historicalElectricity = powerTableMapper.getPowerConsumption(buildingID, roomID);
        Powertable[] powerTableArray = historicalElectricity.toArray(new Powertable[0]);

        ApiJson apiJson = new ApiJson();
        if (historicalElectricity.isEmpty()) {
            apiJson.setReturnCode("400"); // 设置返回码
            apiJson.setReturnMsg("No-data"); // 设置返回信息
            apiJson.setBuildingID(buildingID); // 设置buildingID
            apiJson.setRoomID(roomID); // 设置roomID
            return apiJson;
        } else {
            apiJson.setReturnCode("200"); // 设置返回码
            apiJson.setReturnMsg("Success"); // 设置返回信息
            apiJson.setBuildingID(buildingID); // 设置buildingID
            apiJson.setRoomID(roomID); // 设置roomID
            apiJson.setPowerTable(powerTableArray); // 设置Powertable数组
        }
        return apiJson;
    }

    @ResponseBody
    @GetMapping("/getAvgPowerConsumptionToJson")
    public String getAvgPowerConsumption(String buildingID, String roomID) {
        String avgPowerConsumption = powerTableMapper.getAvgPowerConsumption(buildingID, roomID);


        return avgPowerConsumption;
    }


}
