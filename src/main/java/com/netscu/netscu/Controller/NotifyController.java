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

    @GetMapping("/{PageCount}/{PageNum}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetMyNotify(@PathVariable String PageCount, @PathVariable String PageNum){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        String userId = TokenUtil.getTokenUserId();
        return notifyService.GetMyNotify(userId, PageCount, PageNum);
    }

    @PutMapping("Read/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map SetRead(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return notifyService.SetRead(id, userId);
    }

    @PutMapping("Allread")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map SetAllRead(){
        String userId = TokenUtil.getTokenUserId();
        return notifyService.SetAllRead(userId);

    }
}
