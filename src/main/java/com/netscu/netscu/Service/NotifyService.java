package com.netscu.netscu.Service;

import com.netscu.netscu.Mapper.NotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 14:13
 */
@Service
public class NotifyService {
    @Autowired
    NotifyMapper notifyMapper;

    public List<Map> GetMyNotify(String userId) {
        return notifyMapper.GetMyNotify(userId);
    }

    public Map SetRead(String id, String userId) {
        HashMap<String, Object> result = new HashMap<>();
        if(notifyMapper.SetRead(id, userId) >= 1){
            result.put("success", true);
        }
        else{
            result.put("success", false);
        }
        return result;
    }

    public Boolean AddNotification(String id, String userId, String type) {
        if(notifyMapper.AddNotify(id, userId, type) >= 1){
            return true;
        }
        else {
            return false;
        }
    }
}
