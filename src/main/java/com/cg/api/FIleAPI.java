package com.cg.api;

import com.cg.entity.Image;
import com.cg.service.file.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FIleAPI {
    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping("/image")
    public Image uploadImage(@RequestParam("images") MultipartFile avatar) throws IOException {
        return uploadFileService.saveAvatar(avatar);
    }
}
