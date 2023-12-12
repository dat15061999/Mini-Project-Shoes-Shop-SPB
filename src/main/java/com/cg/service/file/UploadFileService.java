package com.cg.service.file;

import com.cg.entity.Image;
import com.cg.repository.ImageRepository;
import com.cg.util.UploadUtils;
import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service

public class UploadFileService {
    @Autowired
    private  Cloudinary cloudinary;
    @Autowired
    private  ImageRepository imageRepository;
    @Autowired
    private  UploadUtils uploadUtils;

    public Image saveAvatar(MultipartFile image) throws IOException {
        var file = new Image();
        imageRepository.save(file);
        var uploadResult = cloudinary.uploader().upload(image.getBytes(), uploadUtils.buildImageUploadParams(file));
        String fileUrl =(String)uploadResult.get("secure_url");
        file.setUrl(fileUrl);
        imageRepository.save(file);
        return file;
    }
}
