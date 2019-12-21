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

    @Autowired
    NotifyService notifyService;

    @Autowired
    OperationService operationService;


    public Map AddPost(Post post, String Id) {
        HashMap<String, Object> result = new HashMap<>();
        post.setUid(Id);
        if(postMapper.Insert(post) >= 1){
            operationService.AddOperation(post.getUid(), "发布", post.getId());
            result.put("success", true);
            result.put("id", post.getId());
        }
        else {
            result.put("success", false);
        }
        return result;
    }

    public Map ModifyPost(Post post) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.ModifyById(post) >= 1){
            operationService.AddOperation(post.getUid(), "编辑", post.getId());
            result.put("success", true);
            result.put("id", post.getId());
        }
        else {
            result.put("success", false);
        }
        return result;
    }

    public Map DeletePost(String userId, String id) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.DeleteById(userId, id) >= 1){
            operationService.AddOperation(userId, "删帖", id);
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
        Boolean notifyOk;
        if(postMapper.CollectPost(id, userId) >= 1){
            notifyOk = notifyService.AddNotification(id, userId, "收藏");
            operationService.AddOperation(userId, "收藏", id);
            postMapper.IncCollect(id);
            result.put("success", true);
        }
        else {
            notifyOk = false;
            result.put("success", false);
        }
        result.put("notify",notifyOk);
        return result;
    }

    public Map UncollectPost(String id, String userId) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.UncollectPost(id, userId) >= 1){
            operationService.AddOperation(userId, "取消收藏", id);
            postMapper.DecCollect(id);
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        return result;
    }

    public Map LikePost(String id, String userId) {
        HashMap<String, Object> result = new HashMap<>();
        Boolean notifyOk;
        if(postMapper.LikePost(id, userId) >= 1){
            notifyOk = notifyService.AddNotification(id, userId, "点赞");
            operationService.AddOperation(userId, "点赞", id);
            postMapper.IncLike(id);
            result.put("success", true);
        }
        else {
            notifyOk = false;
            result.put("success", false);
        }
        result.put("notify",notifyOk);
        return result;
    }

    public Map UnlikePost(String id, String userId) {
        HashMap<String, Object> result = new HashMap<>();
        if(postMapper.UnlikePost(id, userId) >= 1){
            operationService.AddOperation(userId, "取消点赞", id);
            postMapper.DecLike(id);
            result.put("success", true);
        }
        else {
            result.put("success", false);
        }
        return result;
    }

    public List<Map> GetMyPost(String pageCount, String pageNum, String userId) {
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        Integer to = page*pernum;
        return postMapper.GetMyPost(from, to, Integer.parseInt(userId));
    }

    public Boolean GetLikeStatus(String id, String userId) {
        if(postMapper.GetLikeStatus(id, userId) >= 1){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean GetCollectStatus(String id, String userId) {
        if(postMapper.GetCollectStatus(id, userId) >= 1){
            return true;
        }
        else{
            return false;
        }
    }
}
