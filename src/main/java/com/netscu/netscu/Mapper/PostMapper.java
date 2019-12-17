package com.netscu.netscu.Mapper;

import com.netscu.netscu.Entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 18:37
 */
@Mapper
public interface PostMapper {
    void Insert(Post post);

    Integer ModifyById(Post post);

    Integer DeleteById(String userId, String id);

    List<Map> GetBasicInfo(Integer from, Integer to);

    Map GetInfo(String id);

    Integer AddView(String id);

    Integer CollectPost(String id, String userId);

    Integer UncollectPost(String id, String userId);
}
