package com.StudDept.services;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServices {

    private final Cloudinary cloudinary;

    public Map uploadImage(MultipartFile multipartFile) throws IOException {
        this.cloudinary.uploader().upload(multipartFile.getBytes(), Map.of());
        return null;
    }
}
