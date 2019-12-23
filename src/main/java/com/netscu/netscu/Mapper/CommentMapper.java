package com.netscu.netscu.Mapper;

import com.netscu.netscu.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/8 0008
 * @time 20:56
 */
@Mapper
public interface CommentMapper {
    Integer Insert(Comment comment);
    List<Map> SelBySupportId(Integer supportId, Integer from, Integer perNum);
    Integer DeleteById(String userId, String id);

    List<Map> GetAllComment(String supportId);
}
