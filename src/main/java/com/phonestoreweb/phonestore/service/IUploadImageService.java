package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.dto.response.CloudinaryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


public interface IUploadImageService {
    CloudinaryResponse uploadImage(final MultipartFile imageFile, final String fileName) ;
    CloudinaryResponse uploadAvatarUser(final MultipartFile imageFile, final String fileName) ;
}
