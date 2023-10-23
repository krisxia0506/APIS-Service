# APIS-Service

Air-conditioning Power Inquiry System Service

应急管理大学空调查询系统服务端


## 项目任务

将现有python项目重构为SpringBoot项目

开始重构时间：2023.7.27

## 2023.7.27
工作内容：
* 初始化项目 
* 使用RestTemplate构造发送请求的方法

bug：使用token获取到的session一直是过期的

## 2023.7.28
工作内容：
* 将获取session的方法采用post请求，修复了session一直无效的bug
* 创建[ElectricityLevelController.java](src%2Fmain%2Fjava%2Fcom%2Fxiajiayi%2Fapisservice%2Fcontroller%2FElectricityLevelController.java)类和成员方法`electricityLevel`，用于获取电量，接口为`/electricityLevel`，参数为buildingID和roomID ，返回值为`WanXiaoJson`类

## 2023.7.29
工作内容：
* 增加功能获取历史电量，方法`getHistoricalElectricity`
  * 接口`/getHistoricalElectricity`，参数为buildingID和roomID，返回值为数组，数组中每个元素为string类型的power
  * 接口`/getHistoricalElectricityToJson`，参数为buildingID和roomID，返回值为Json，key为日期，value为电量
* 增加功能 获取历史耗电量，方法`getPowerConsumption`
  * 接口`/getPowerConsumptionToJson`，参数为buildingID和roomID，返回值为Json，key为日期，value为电量

## 2023.7.30
工作内容：
* 增加功能 获取历史耗电量，方法`getAvgPowerConsumption`
  * 接口`/getAvgPowerConsumptionToJson`，参数为buildingID和roomID，返回值为Json，key为日期，value为电量
* 修改获取token，由静态改为从数据库动态获取，便于维护
* 和前端对接