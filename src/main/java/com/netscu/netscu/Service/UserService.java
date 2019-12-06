package com.netscu.netscu.Service;

import com.netscu.netscu.Common.Token;
import com.netscu.netscu.Entity.User;
import com.netscu.netscu.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/4 0004
 * @time 15:05
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User SelById(String id){
        return userMapper.SelById(id);
    }

    public Map Add(User user){
        HashMap<String, Object> result = new HashMap<>();
        if(null != user.getEmail()&& null != userMapper.SelByEmail(user.getEmail())){
            System.out.println("1");
            result.put("success", false);
        }
        else if(null != user.getMobile()&& null != userMapper.SelByMobile(user.getMobile())){
            System.out.println("2");
            result.put("success",false);
        }
        else{
            userMapper.Insert(user);
//            String Id = userMapper.Insert(user).getId();
//            user.setId(Id);
            System.out.println(user.getId());
            String token = Token.createJWT(user);
            result.put("success",true);
            result.put("token", token);
        }
        return result;
    }

    public List<Map> Test(){
        return userMapper.Test();
    }

    public Map Login(User user) {
        HashMap<String, Object> result = new HashMap<>();
        String token = null;
        List<User> returnUsers = userMapper.SelByName(user.getName());
        for(User U: returnUsers){
            if(U.getPassword().equals(user.getPassword())){
                token = Token.createJWT(U);
                break;
            }
        }
        if(null != token){
            result.put("token", token);
            result.put("success", true);
        }
        else{
            result.put("success", false);
        }
        return result;
    }
}
