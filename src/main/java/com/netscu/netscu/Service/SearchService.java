package com.netscu.netscu.Service;

import com.netscu.netscu.Mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HugoAhoy
 * @date 2019/12/24 0024
 * @time 20:38
 */
@Service
public class SearchService {
    @Autowired
    SearchMapper searchMapper;


    public List<Map> GetBasicInfo(String q, String pageCount, String pageNum) {
        HashMap<String, Object> map  = new HashMap<>();
        Integer page = Integer.parseInt(pageCount);
        Integer pernum = Integer.parseInt(pageNum);
        Integer from = (page-1)*pernum;
        String allKey[] = q.split("\\+");
        String Q = "%";
        for(Integer i = 0; i < allKey.length; i ++){
            allKey[i] = "%"+allKey[i]+"%";
        }
        System.out.println("Q:"+allKey.toString());
        map.put("Q",allKey);
        map.put("from", from);
        map.put("perNum", pernum);
        return searchMapper.GetBasicInfo(map);
    }
}
