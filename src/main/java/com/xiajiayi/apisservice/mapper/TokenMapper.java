package com.xiajiayi.apisservice.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 夏佳怡
 * @Date 2023/7/30 16:14
 * @Version 1.0
 * Description:
 */
@Mapper
public interface TokenMapper {
    /**
     * 在数据表token中查询token
     */
    public String getToken();
}
