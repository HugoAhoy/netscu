package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Entity.Post;
import com.netscu.netscu.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 18:26
 */
@CrossOrigin
@RequestMapping("/Post")
@RestController
public class PostController {
    @Autowired
    PostService postService;

    @CrossOrigin
    @PostMapping("Add")
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map AddPost(@RequestBody Post post){
        String userId = TokenUtil.getTokenUserId();
        return postService.AddPost(post, userId);
    }

    @CrossOrigin
    @PutMapping("Modify")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map ModifyPost(@RequestBody Post post){
        String userId = TokenUtil.getTokenUserId();
        post.setUid(userId);
        return postService.ModifyPost(post);
    }

    @CrossOrigin
    @PutMapping("Delete/{id}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map DeletePost(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return postService.DeletePost(userId, id);
    }

    @CrossOrigin
    @GetMapping("BasicInfo/{PageCount}/{PageNum}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetBasicInfo(@PathVariable String PageCount, @PathVariable String PageNum){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = postService.GetBasicInfo(PageCount, PageNum);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }

    @CrossOrigin
    @GetMapping("Info/{id}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetInfo(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        Map result = postService.GetInfo(id);
        System.out.println(result);
        System.out.println(result.get("uid"));
        String postUid = String.valueOf((Long)result.get("uid"));
        System.out.println("postUid"+postUid);
        result.put("isOwner",postUid.equals(userId));
        Boolean Liked =postService.GetLikeStatus(id, userId);
        Boolean Collected = postService.GetCollectStatus(id, userId);
        result.put("isLiked",Liked);
        result.put("isCollected", Collected);

        return result;
    }

    @CrossOrigin
    @PostMapping("Collect/{id}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map CollectPost(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return postService.CollectPost(id, userId);
    }

    @PutMapping("Uncollect/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map UncollectPost(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return postService.UncollectPost(id, userId);
    }

    @CrossOrigin
    @PostMapping("Like/{id}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map LikePost(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return postService.LikePost(id, userId);
    }

    @PutMapping("Unlike/{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map UnlikePost(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        return postService.UnlikePost(id, userId);
    }

    @CrossOrigin
    @GetMapping({"UserPost/{PageCount}/{PageNum}"})
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetMyPost(@PathVariable String PageCount, @PathVariable String PageNum){
        String userId = TokenUtil.getTokenUserId();
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = postService.GetMyPost(PageCount, PageNum, userId);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }

    @CrossOrigin
    @GetMapping({"UserPost/{PageCount}/{PageNum}/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Map GetOthersPost(@PathVariable String PageCount, @PathVariable String PageNum, @PathVariable String id){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = postService.GetMyPost(PageCount, PageNum, id);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }

    @CrossOrigin
    @GetMapping({"Collection/{PageCount}/{PageNum}/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Map GetOthersCollection(@PathVariable String PageCount, @PathVariable String PageNum, @PathVariable String id){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = postService.GetCollection(PageCount, PageNum, id);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }

    @CrossOrigin
    @GetMapping({"Collection/{PageCount}/{PageNum}"})
    @ResponseStatus(HttpStatus.OK)
    public Map GetMyCollection(@PathVariable String PageCount, @PathVariable String PageNum){
        String userId = TokenUtil.getTokenUserId();
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = postService.GetCollection(PageCount, PageNum, userId);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }

    @CrossOrigin
    @GetMapping("BasicInfoByChannel/{PageCount}/{PageNum}/{id}")
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetBasicInfoByChannel(@PathVariable String PageCount, @PathVariable String PageNum, @PathVariable String id){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = postService.GetBasicInfoByChannel(PageCount, PageNum, id);
        if(Data.size() == Integer.parseInt(PageNum)){
            result.put("Finish",false);
        }
        else{
            result.put("Finish",true);
        }
        result.put("data",Data);
        return result;
    }

}
