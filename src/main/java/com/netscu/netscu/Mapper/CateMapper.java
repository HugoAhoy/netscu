package com.netscu.netscu.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 0:00
 */

@Mapper
public interface CateMapper {
    List<Map> GetBasicInfo();

    Map SelById(String id);
}
