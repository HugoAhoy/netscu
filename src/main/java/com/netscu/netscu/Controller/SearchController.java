package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/24 0024
 * @time 20:37
 */
@RestController
@CrossOrigin
@RequestMapping("/Search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @CrossOrigin
    @GetMapping("BasicInfo/{Q}/{PageCount}/{PageNum}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetBasicInfo(@PathVariable String PageCount, @PathVariable String PageNum, @PathVariable String Q){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = searchService.GetBasicInfo(Q, PageCount, PageNum);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }
}
