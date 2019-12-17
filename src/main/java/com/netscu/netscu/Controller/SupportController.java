package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 11:07
 */
@RestController
@CrossOrigin
@RequestMapping("/Support")
public class SupportController {
    @Autowired
    SupportService supportService;

    @GetMapping("PostId/{id}/{pageCount}/{pageNum}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetBasicInfo(@PathVariable String id,@PathVariable String pageCount, @PathVariable String pageNum){
        String userId = TokenUtil.getTokenUserId();
        System.out.println("Page："+pageCount);
        System.out.println("NumPerPage："+pageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = supportService.GetInfo(userId, id, pageCount, pageNum);
        if(Data.size() == Integer.parseInt(pageCount)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("uid", userId);
        result.put("data",Data);
        return result;
    }

}
