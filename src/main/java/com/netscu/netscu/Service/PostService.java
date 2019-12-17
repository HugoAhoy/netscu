package com.netscu.netscu.Service;

import com.netscu.netscu.Entity.Post;
import com.netscu.netscu.Mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 18:37
 */
@Service
public class PostService {
    @Autowired
    PostMapper postMapper;
    NotifyService notifyService;

    public Map AddPost(Post post, String Id) {
        HashMap<String, Object> result = new HashMap<>();
        post.setUid(Id);
        postMapper.Insert(post);
        result.put("success", true);
        result.put("id", post.getId());
        return result;
    }

    public Map ModifyPost(Post post) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.ModifyById(post) >= 1){
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        return result;
    }

    public Map DeletePost(String userId, String id) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.DeleteById(userId, id) >= 1){
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        return result;
    }


    public List<Map> GetBasicInfo(String pageCount, String pageNum) {
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        Integer to = page*pernum;
        return postMapper.GetBasicInfo(from, to);
    }

    public Map GetInfo(String id) {
        postMapper.AddView(id);
        return postMapper.GetInfo(id);
    }

    public Map CollectPost(String id, String userId) {
        HashMap<String, Object> result = new HashMap<>();
        Boolean notifyOk = notifyService.AddNotification(id, userId, "点赞");

        if(postMapper.CollectPost(id, userId) >= 1){
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        result.put("notify",notifyOk);
        return result;
    }

    public Map UncollectPost(String id, String userId) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.UncollectPost(id, userId) >= 1){
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        return result;
    }
}
