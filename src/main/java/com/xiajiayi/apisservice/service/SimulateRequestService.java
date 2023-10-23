package com.xiajiayi.apisservice.service;

import com.xiajiayi.apisservice.mapper.TokenMapper;
import com.xiajiayi.apisservice.pojo.WanXiaoJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author 夏佳怡
 * @Date 2023/7/27 21:05
 * @Version 1.0
 * Description:
 * 用于模拟请求，向完美校园服务器发送请求，获取数据
 */
@Slf4j
@Service
public class SimulateRequestService {
    @Autowired
    TokenMapper tokenMapper;
    // 存储session
    private static String sessionValue;
    // 存储token
    private static String tokenValue;
    // 创建一个HashMap来转义buildID
    private static final Map<String, String> escapingBuildingID = new HashMap<>();

    static {
        // 添加键值对到HashMap中
        escapingBuildingID.put("1", "1-9--10-");
        escapingBuildingID.put("2", "1-10--11-");
        escapingBuildingID.put("3", "1-11--12-");
        escapingBuildingID.put("4", "1-12--9-");
        escapingBuildingID.put("5", "1-3--3-");
        escapingBuildingID.put("6", "1-1--1-");
        escapingBuildingID.put("7", "1-4--8-");
        escapingBuildingID.put("8", "1-5--4-");
        escapingBuildingID.put("9", "1-6--5-");
        escapingBuildingID.put("10", "1-7--6-");
        escapingBuildingID.put("11", "1-8--7-");
    }

    /**
     * 使用正则表达式提取"SESSION"字段的值
     *
     * @param cookieHeaderValue 从响应头中获取的cookie
     * @return 返回sessionValue
     */
    private static String extractSessionValue(String cookieHeaderValue) {
        // 定义正则表达式
        String regex = "SESSION=([a-zA-Z0-9-]+);";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建Matcher对象
        Matcher matcher = pattern.matcher(cookieHeaderValue);
        // 查找匹配
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null; // 如果未找到匹配的"SESSION"字段，返回null或者抛出异常进行处理
        }
    }

    /**
     * 根据token获取session
     *
     * @return 返回session
     */
    public String getSessionByToken() {
        //在数据库获取token
        String token = tokenMapper.getToken();
        log.info("由于session过期了，从新从数据库获取token:{}", token);
        tokenValue = token;
        //创建一个RestTemplate的实例
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://h5cloud.17wanxiao.com:8080/CloudPayment/user/pay.do?versioncode=10556102&systemType=IOS&UAinfo=wanxiao&token=" + tokenValue + "&customerId=104"; //设置请求的URL
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //发送post请求，并携带请求头
        ResponseEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<>(headers), String.class);
        //获取cookie中的session字段
        String cookieHeaderValue = response.getHeaders().get("Set-Cookie").get(0);
        // 使用正则表达式提取"SESSION"字段的值
        String sessionValue = extractSessionValue(cookieHeaderValue);
        log.info("此次请求的sessionValue:{}", sessionValue);
        return sessionValue;
    }

    /**
     * 发送请求给完美校园，获取电量
     * 首先创建了一个RestTemplate的实例，然后设置了请求的URL，并使用getForObject方法发送GET请求。最后将返回的结果打印出来。
     *
     * @param buildingID 宿舍楼ID
     * @param roomID     房间ID
     * @return 返回电量
     */
    public WanXiaoJson sendRequestToQueryPowerLevel(String buildingID, String roomID) {

        log.info("开始发送请求给完美校园，获取电量--------------------------------");
        RestTemplate restTemplate = new RestTemplate();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host", "h5cloud.17wanxiao.com:8080");
        headers.add("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 15_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 Wanxiao/5.5.6 CCBSDK/2.4.0");
        headers.add("Accept", "application/json");
        headers.add("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.add("Accept-Encoding", "gzip,deflate");
        headers.add("Referer", "http://h5cloud.17wanxiao.com:8080/CloudPayment/bill/selectPayProject.do?txcode=2&interurl=substituted_pay&payProId=2124&amtflag=0&payamt=0&payproname=%E8%B4%AD%E7%94%B5&img=http://cloud.17wanxiao");
        headers.add("Connection", "keep-alive");
        headers.add("Cookie", "SESSION=" + sessionValue + "; SERVERID=e8e02aa88506006460462b373a5d91a9|1653700198|1653700055");
        String url = "http://h5cloud.17wanxiao.com:8080/CloudPayment/user/getRoomState.do?payProId=2124&schoolcode=104&businesstype=2&roomverify=" + escapingBuildingID.get(buildingID) + roomID;
        log.info("此次发送的请求url为:{}", url);
        try {
            WanXiaoJson response = restTemplate
                    .postForObject(url, new HttpEntity<>(headers), WanXiaoJson.class);
            log.info("此次查询的楼号buildingID:{}  房间号roomID:{}  查询的电量为：{}", buildingID, roomID, response.getQuantity());
            log.info("太好了，此次请求的session没有过期，session为:{}", sessionValue);
            return response;
        } catch (Exception e) {
            try {
                log.info("马勒戈壁，session过期了！！！！重新获取sessionValue并保存:{}", sessionValue);
                sessionValue = getSessionByToken();
                headers.set("Cookie", "SESSION=" + sessionValue + "; SERVERID=e8e02aa88506006460462b373a5d91a9|1653700198|1653700055");
                WanXiaoJson response = restTemplate.postForObject(url, new HttpEntity<>(headers), WanXiaoJson.class);
                log.info("此次查询的楼号buildingID:{}  房间号roomID:{}  查询的电量为：{}", buildingID, roomID, response.getQuantity());
                return response;
            } catch (Exception e1) {
                log.error("重新获取session后还是，请求失败，呼叫管理员来查看问题");
                // 输出此次请求的信息
                log.info("此次请求的url为:{}", url);
                log.info("此次请求的headers为:{}", headers);
                log.info("此次请求的sessionValue为:{}", sessionValue);
                log.info("此次请求的tokenValue为:{}", tokenValue);
                log.info("此次请求的buildingID为:{}", buildingID);
                log.info("此次请求的roomID为:{}", roomID);
                log.info("此次请求的response为:{}", restTemplate.postForObject(url, new HttpEntity<>(headers), String.class));
                return null;
            }
        }
    }
}
