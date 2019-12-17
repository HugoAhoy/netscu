package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.security.jgss.TokenTracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 14:12
 */
@RestController
@RequestMapping("/Notify")
public class NotifyController {
    @Autowired
    NotifyService notifyService;

    @GetMapping("Mine")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetMyNotify(){
        HashMap<String, Object> result = new HashMap<>();
        String userId = TokenUtil.getTokenUserId();
        List<Map> Data = notifyService.GetMyNotify(userId);
        result.put("data",Data);
        return result;
    }

    @PutMapping("Read/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map SetRead(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return notifyService.SetRead(id, userId);
    }
}
