package com.netscu.netscu.Service;

import com.netscu.netscu.Mapper.OperationMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/21 0021
 * @time 15:39
 */
@Service
public class OperationService {
    @Autowired
    OperationMapper operationMapper;

    public Boolean AddOperation(String userId, String type, String PostId){
        return operationMapper.AddOperation(userId, type, PostId) >= 1;
    }

    public Map GetOperation(String userId, String pageCount, String pageNum){
        HashMap<String ,Object> result = new HashMap<>();
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        Integer to = page*pernum;
        Integer uid = Integer.parseInt(userId);
        List<Map> Data = operationMapper.GetOperation(uid, from, to);
        if(Data.size() < pernum){
            result.put("Finish", true);
        }
        else {
            result.put("Finish", false);
        }
        result.put("data", Data);
        return result;
    }




}
