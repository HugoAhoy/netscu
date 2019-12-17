package com.netscu.netscu.Service;

import com.netscu.netscu.Mapper.SupportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/11 0011
 * @time 11:08
 */
@Service
public class SupportService {
    @Autowired
    SupportMapper supportMapper;

    public List<Map> GetInfo(String uId, String pId, String pageCount, String pageNum) {
        Integer userId = Integer.parseInt(uId);
        Integer postId = Integer.parseInt(pId);
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        Integer to = page*pernum;
        return supportMapper.GetInfo(userId, postId, from, to);
    }
}
