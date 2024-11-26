package com.phonestoreweb.phonestore.service.implService;

import com.cloudinary.Cloudinary;
import com.phonestoreweb.phonestore.dto.response.CloudinaryResponse;
import com.phonestoreweb.phonestore.service.IUploadImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadImageService implements IUploadImageService {
    private final Cloudinary cloudinary;

    public UploadImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    @Transactional
    public CloudinaryResponse uploadImage(MultipartFile imageFile, String fileName) {
        try {
            final Map result = this.cloudinary.uploader().upload(imageFile.getBytes(),
                    Map.of("public_id",
                    "dailedev/product/"+fileName));
            final String url = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            return CloudinaryResponse.builder().publicId(publicId).url(url)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional
    public CloudinaryResponse uploadAvatarUser(MultipartFile imageFile, String fileName) {
        try {
            final Map result = this.cloudinary.uploader().upload(imageFile.getBytes(),
                    Map.of("public_id",
                            "dailedev/userAvatar/"+fileName));
            final String url = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            return CloudinaryResponse.builder().publicId(publicId).url(url)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
