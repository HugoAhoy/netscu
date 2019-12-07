package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Entity.User;
import com.netscu.netscu.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/4 0004
 * @time 15:03
 */
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("getUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String GetUserById(@PathVariable String id) {
        return userService.SelById(id).toString();
    }

    @PostMapping("Registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Map AddUser(@RequestBody User user){
        System.out.println(user.toString());
        return userService.Add(user);
    }

    @PostMapping("Login")
    @ResponseStatus(HttpStatus.OK)
    public Map LoginUser(@RequestBody User user){
        return userService.Login(user);
    }


    @RequestMapping("LogTokenTest")
    @UserLoginToken
    public String LogTokenTest(){
        String userId =TokenUtil.getTokenUserId();
        System.out.println(userId);
        return "token通过验证， uid 为"+userId;
    }

    @RequestMapping("test")
    public List<Map> Test(){
        return userService.Test();
    }
}
