package com.netscu.netscu.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 11:09
 */
@Mapper
public interface SupportMapper {
    List<Map> GetInfo(Integer uid, Integer id, Integer from, Integer to);
}
