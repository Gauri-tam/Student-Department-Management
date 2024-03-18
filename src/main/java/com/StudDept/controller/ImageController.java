package com.StudDept.controller;


import com.StudDept.services.ImageServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServices imageServices;


    @PostMapping
    public ResponseEntity<Map> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
      Map date = this.imageServices.uploadImage(multipartFile);
      return ResponseEntity.ok(date);
    }
}
