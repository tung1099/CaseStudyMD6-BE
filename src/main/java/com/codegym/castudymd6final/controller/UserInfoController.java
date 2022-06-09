package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.dto.AvatarForm;
import com.codegym.castudymd6final.model.entity.UserInfo;
import com.codegym.castudymd6final.service.userInfo.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/userInfo")
@CrossOrigin("*")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @Value("${file-upload}")
    private String uploadPath;

    @PostMapping("/create")
    public ResponseEntity<UserInfo> createNewUserInfo(@ModelAttribute UserInfo userInfo) {
        userInfo.setAvatar("avatar.jpg");
        userInfoService.save(userInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/avatar/{id}")
    public ResponseEntity<UserInfo> editAvatar(@PathVariable Long id, @ModelAttribute AvatarForm avatarForm) {
        MultipartFile multipartFile = avatarForm.getAvatar();
        String image = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userInfoService.findById(id).get();
        userInfo.setAvatar(image);
        userInfoService.save(userInfo);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserInfo> updateProfile(@PathVariable Long id, @ModelAttribute UserInfo userInfo) {
        userInfo.setId(id);
        userInfo.setAvatar(userInfoService.findById(id).get().getAvatar());
        userInfoService.save(userInfo);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserInfo> deleteUserInfo(@PathVariable Long id) {
        userInfoService.removeById(id);
        return new ResponseEntity<>(userInfoService.findById(id).get(), HttpStatus.OK);
    }
}
