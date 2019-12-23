package com.netscu.netscu.Controller;

import com.netscu.netscu.Annotations.UserLoginToken;
import com.netscu.netscu.Common.Token;
import com.netscu.netscu.Common.TokenUtil;
import com.netscu.netscu.Entity.Comment;
import com.netscu.netscu.Service.CommentService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/8 0008
 * @time 20:55
 */
@RestController
@RequestMapping("/Comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("Add")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.CREATED)
    public Map AddComment(@RequestBody Comment comment){
        String userId = TokenUtil.getTokenUserId();
        comment.setUid(userId);
        return commentService.AddComment(comment);
    }

    @GetMapping("{supportId}/{PageCount}/{PageNum}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetComment(@PathVariable String supportId, @PathVariable String PageCount, @PathVariable String PageNum){
        System.out.println("Page："+PageCount);
        System.out.println("NumPerPage："+PageNum);
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = commentService.GetComment(supportId, PageCount, PageNum);
        if(Data.size() < Integer.parseInt(PageNum)){
            result.put("Finish",true);
        }
        else{
            result.put("Finish",false);
        }
        result.put("data",Data);
        return result;
    }

    @GetMapping("{supportId}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map GetAllComment(@PathVariable String supportId){
        HashMap<String ,Object> result = new HashMap<>();
        List<Map> Data = commentService.GetAllComment(supportId);
        result.put("data",Data);
        return result;
    }


    @PutMapping("{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public void DeleteComment(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        commentService.DeleteComment(userId, id);
    }

}
