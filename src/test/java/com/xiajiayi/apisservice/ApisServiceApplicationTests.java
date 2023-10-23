package com.xiajiayi.apisservice;

import com.xiajiayi.apisservice.mapper.PowerTableMapper;
import com.xiajiayi.apisservice.pojo.Powertable;
import com.xiajiayi.apisservice.service.SimulateRequestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
@SpringBootTest
class ApisServiceApplicationTests {
    @Autowired
    SimulateRequestService simulateRequestService;


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PowerTableMapper powerTableMapper;

    @Test
    void testSendRequestToQueryPowerLevel() {
        simulateRequestService.sendRequestToQueryPowerLevel("1", "101");
    }

    @Test
    void testGetHistoricalElectricity() {

        log.info("历史记录：{}", powerTableMapper.getHistoricalElectricity("1","101"));
        List<Powertable> historicalElectricity = powerTableMapper.getHistoricalElectricity("1", "101");
        for (Powertable powertable : historicalElectricity) {
            log.info("日期：{},power:{}",powertable.getDate(),powertable.getPower());
        }
    }

}
