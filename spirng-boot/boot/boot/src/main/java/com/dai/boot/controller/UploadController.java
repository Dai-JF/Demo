package com.dai.boot.controller;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dai.jf
 * @date 2021/10/27-11:16
 * @description: 文件上传测试
 */
@Slf4j
@Controller
public class UploadController {

  @GetMapping("/form_layouts.html")
  public String formLayouts() {
    return "form/form_layouts";
  }

  /**
   * Description: MultipartFile: 自动封装上传过来的文件
   *
   * @param email
   * @param username
   * @param img
   * @param photo
   * @return String
   * @auther dai.jf
   * @date 2021/10/29 - 13:37
   */
  @PostMapping("upload")
  public String upload(@RequestParam("email") String email,
      @RequestParam("username") String username,
      @RequestPart("img") MultipartFile img,
      @RequestPart("photo") MultipartFile[] photo) throws IOException {

    log.info("信息：email={},username={},img={},photo={}", email, username, img.getSize(),
        photo.length);

    if (!img.isEmpty()) {
      //保存文件到磁盘
      String originalFilename = img.getOriginalFilename();
      img.transferTo(new File("D:\\test\\" + originalFilename));
    }

    if (photo.length > 0) {
      for (MultipartFile ph : photo){
        if (!ph.isEmpty()){
          String originalFilename = ph.getOriginalFilename();
          ph.transferTo(new File("D:\\test\\" + originalFilename));
        }
      }
    }
    return "main";
  }
}
