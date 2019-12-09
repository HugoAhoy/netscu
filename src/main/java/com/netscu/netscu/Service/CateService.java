package com.netscu.netscu.Service;

import com.netscu.netscu.Mapper.CateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 0:00
 */

@Service
public class CateService {
    @Autowired
    CateMapper cateMapper;

    public Map GetBasicInfo() {
        HashMap<String, Object> result = new HashMap<>();
        List<Map> Data = cateMapper.GetBasicInfo();
        result.put("data", Data);
        return  result;
    }

    public Map GetInfo(String id) {
        return cateMapper.SelById(id);
    }
}
