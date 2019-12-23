package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Entity.User;
import com.netscu.netscu.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("Registration")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public Map AddUser(@RequestBody User user){
        System.out.println(user.toString());
        return userService.Add(user);
    }

    @PostMapping("Login")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public Map LoginUser(@RequestBody User user){
        return userService.Login(user);
    }

    @GetMapping("Info")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetMyInfo(){
        String userId =TokenUtil.getTokenUserId();
        return userService.SelByInfoId(userId, userId);
    }

    @GetMapping("Info/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetOtherInfo(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return userService.SelByInfoId(id, userId);
    }

    @PutMapping("Modify")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map ModifyMyInfo(@RequestBody User user){
        String userId = TokenUtil.getTokenUserId();
        user.setId(userId);
        return userService.ModifyMyInfo(user);
    }

    @PostMapping("Follow")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map FollowUser(@RequestBody User fans){
        String Id = fans.getId();
        String userId = TokenUtil.getTokenUserId();
        return userService.FollowById(userId, Id);
    }

    @PostMapping("Unfollow")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map UnfollowUser(@RequestBody User fans){
        String Id = fans.getId();
        String userId = TokenUtil.getTokenUserId();
        return userService.UnfollowById(userId, Id);
    }


    @GetMapping("MyFollow/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map Getfollower(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return userService.GetFollow(id, userId);
    }

    @GetMapping("MyFollow")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map Getfollower(){
        String userId = TokenUtil.getTokenUserId();
        return userService.GetFollow(userId, userId);
    }

    @GetMapping("MyFan/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map Getfan(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return userService.GetFan(id, userId);
    }

    @GetMapping("MyFan")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map Getfan(){
        String userId = TokenUtil.getTokenUserId();
        return userService.GetFan(userId, userId);
    }



    @RequestMapping("LogTokenTest")
    @CrossOrigin
    @UserLoginToken
    public String LogTokenTest(){
        String userId =TokenUtil.getTokenUserId();
        System.out.println(userId);
        return "token通过验证， uid 为"+userId;
    }

    @RequestMapping("test")
    @CrossOrigin
    public List<Map> Test(){
        return userService.Test();
    }
}
