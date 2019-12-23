package com.netscu.netscu.Controller;

import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Service.OperationService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/21 0021
 * @time 16:15
 */
@CrossOrigin
@RestController
@RequestMapping("/Operation")
public class OperationController {
    @Autowired
    OperationService operationService;

    @GetMapping("{PageCount}/{PageNum}")
    @ResponseStatus(HttpStatus.OK)
    public Map GetMyOperation(@PathVariable String PageCount, @PathVariable String PageNum){
        String userId = TokenUtil.getTokenUserId();
        return operationService.GetOperation(userId, PageCount, PageNum);
    }

    @GetMapping("{PageCount}/{PageNum}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map GetOthersOperation(@PathVariable String PageCount, @PathVariable String PageNum, @PathVariable String id){
        return operationService.GetOperation(id, PageCount, PageNum);
    }


    @GetMapping("Test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map AddOperation(@PathVariable String id){
        String Type = "点赞";
        String userId = TokenUtil.getTokenUserId();
        HashMap<String, Boolean> result = new HashMap<>();
        if(operationService.AddOperation(userId, Type, id) == true){
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        return  result;
    }

}
