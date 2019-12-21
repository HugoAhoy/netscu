package com.netscu.netscu.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/21 0021
 * @time 15:39
 */
@Mapper
public interface OperationMapper {
    Integer AddOperation(String userId, String type, String postId);

    List<Map> GetOperation(Integer uid, Integer from, Integer to);
}
