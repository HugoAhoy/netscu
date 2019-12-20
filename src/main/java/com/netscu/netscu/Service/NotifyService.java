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

    public Map GetMyNotify(String userId, String pageCount, String pageNum) {
        HashMap<String, Object> result = new HashMap<>();
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        Integer to = page*pernum;
        Integer uid = Integer.parseInt(userId);
        List<Map> Data = notifyMapper.GetMyNotify(uid, from, to);
        result.put("data", Data);
        Integer Unread = notifyMapper.GetMyUnread(uid);
        result.put("Unread", Unread);
        return result;
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
        System.out.println(id);
        System.out.println(userId);
        System.out.println(type);
        if(notifyMapper.AddNotify(id, userId, type) >= 1){
            return true;
        }
        else {
            return false;
        }
    }
}
