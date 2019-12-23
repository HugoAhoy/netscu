package com.netscu.netscu.Service;

import com.netscu.netscu.Entity.Comment;
import com.netscu.netscu.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/8 0008
 * @time 20:57
 */

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public Map AddComment(Comment comment) {
        HashMap<String, Object> result = new HashMap<>();
        if( commentMapper.Insert(comment) >= 1){
            result.put("success", true);
        }
        else{
            result.put("success", false);
        }
        return result;
    }

    public List<Map> GetComment(String supportId, String pageCount, String pageNum) {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("data", Data);
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        Integer to = page*pernum;
        Integer sid = Integer.parseInt(supportId);
        return commentMapper.SelBySupportId(sid,from, to);
    }

    public void DeleteComment(String userId, String id) {
        if(commentMapper.DeleteById(userId, id) >= 1){
        }
        else {
            throw new RuntimeException("无可用的资源");
        }
    }

    public List<Map> GetAllComment(String supportId) {
        return commentMapper.GetAllComment(supportId);
    }
}
