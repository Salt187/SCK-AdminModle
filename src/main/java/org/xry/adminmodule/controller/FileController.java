package org.xry.adminmodule.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xry.adminmodule.pojo.Code;
import org.xry.adminmodule.pojo.Result;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/File")
public class FileController {

    //上传文件
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //UUID制作特殊命名
        fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File("D:\\Project file\\File\\"+fileName));
        return new Result(null,"上传成功", Code.INSERT_OK);
    }

}
