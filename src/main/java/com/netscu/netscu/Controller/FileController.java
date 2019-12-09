package com.netscu.netscu.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author HugoAhoy
 * @date 2019/12/9 0009
 * @time 11:41
 */
@RestController
@RequestMapping("/File")
public class FileController {
    @PostMapping("Image")
    @CrossOrigin
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map handleFileUpload(@RequestParam("file") MultipartFile file) {
        HashMap<String ,Object> result = new HashMap<>();
        String fileName = UUID.randomUUID()+ file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                "D:\\GitRepository\\NETSCU\\upload\\"+
                                fileName)));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                result.put("success",false);
                result.put("error","上传失败," + e.getMessage());
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result.put("success",false);
                result.put("error","上传失败," + e.getMessage());
                return result;
            }
            result.put("success",true   );
            result.put("url", "http://localhost:8080/img/"+fileName);
            return result;
        } else {
            result.put("success",false);
            result.put("error","上传失败，因为文件是空的.");
            return result;
        }
    }
}
