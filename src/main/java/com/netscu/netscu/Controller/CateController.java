package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 0:02
 */
@RestController
@RequestMapping("/Cate")
public class CateController {
    @Autowired
    CateService cateService;

    @GetMapping("BasicInfo")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetBasicInfo(){
        return cateService.GetBasicInfo();
    }

    @GetMapping("Info/{Id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetInfo(@PathVariable String Id){
        return cateService.GetInfo(Id);
    }
}
