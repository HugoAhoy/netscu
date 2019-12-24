package com.netscu.netscu.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/24 0024
 * @time 20:39
 */
@Mapper
public interface SearchMapper {

    List<Map> GetBasicInfo(HashMap<String, Object> map);
}
