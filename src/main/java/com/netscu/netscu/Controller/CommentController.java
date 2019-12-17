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

    @GetMapping("{supportId}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public Map AddComment(@PathVariable String supportId){
        return commentService.GetComment(supportId);
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @UserLoginToken
    @ResponseStatus(HttpStatus.OK)
    public void DeleteComment(@PathVariable String id){
        String userId = TokenUtil.getTokenUserId();
        commentService.DeleteComment(userId, id);
    }

}
