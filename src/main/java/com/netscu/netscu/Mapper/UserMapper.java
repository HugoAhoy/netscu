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
    Map SelInfoById(String id);
    Integer Insert(User user);
    List<User> SelByName(String name);
    User SelByMobile(String Mobile);
    User SelByEmail(String Email);
    Integer GetFansNum(String Id);
    Integer GetCollectionNum(String Id);
    Integer GetPostNum(String Id);
    Integer GetFollowed(String followId, String fanId);
    Integer GetFollowNum(String Id);
    Integer ModifyInfoById(User user);
    Integer FollowById(String userId, String id);
    Integer UnfollowById(String userId, String id);

    List<Map> Test();

    List<Map> GetFollower(String id, String userId);

    List<Map> GetFan(String id, String userId);
}
