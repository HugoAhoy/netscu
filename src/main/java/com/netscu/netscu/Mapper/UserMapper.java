package com.netscu.netscu.Mapper;

import com.netscu.netscu.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/4 0004
 * @time 15:05
 */
@Mapper
public interface UserMapper {
    User SelById(String Id);
    Integer Insert(User user);
    List<User> SelByName(String name);
    User SelByMobile(String Mobile);
    User SelByEmail(String Email);

    List<Map> Test();
}
