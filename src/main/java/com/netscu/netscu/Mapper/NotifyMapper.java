package com.netscu.netscu.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 14:30
 */
@Mapper
public interface NotifyMapper {

    List<Map> GetMyNotify(Integer userId,Integer from,Integer perNum);

    Integer SetRead(String id, String userId);

    Integer AddNotify(String id, String userId, String type);

    Integer GetMyUnread(Integer uid);

    Integer SetAllRead(String userId);
}
