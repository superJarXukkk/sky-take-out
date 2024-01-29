package com.sky.controller.admin;


import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api("常规功能模块")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<String> uploadPicture(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        //uuid
        String[] split = originalFilename.split("\\.");
        String filePath = UUID.randomUUID() + split[0];
        String upload = null;
        try {
            upload = aliOssUtil.upload(file.getBytes(), filePath);
            return Result.success(upload);
        } catch (IOException e) {
            log.info("文件上传失败");
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

    }
}
