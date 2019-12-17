package com.netscu.netscu.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 14:30
 */
public interface NotifyMapper {

    List<Map> GetMyNotify(String userId);

    Integer SetRead(String id, String userId);

    Integer AddNotify(String id, String userId, String type);
}
