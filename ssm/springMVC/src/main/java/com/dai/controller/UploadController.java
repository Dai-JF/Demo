package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: dai.jf
 * @date: 2021/8/24 3:23
 * @description:
 */
@Controller
public class UploadController {
  /**
   * @RequestParam 默认是，不写也是,前提username和表单的name属性值相同
   *
   * <p>单文件
   */
  @RequestMapping("/up")
  public String uploadFile(
      @RequestParam("img") MultipartFile file, @RequestParam("username") String username, Model m) {
    // 文件保存
    try {
      file.transferTo(new File("D:\\" + file.getOriginalFilename()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "success";
  }

  /**
   * 多文件上传
   *
   * <p>且表单内的file类型的name属性都是img
   *
   * <p>若有其他的name值，就在下面方法内多加一个MultipartFile[]
   */
  @RequestMapping("/upload")
  public String uploadFiles(
      @RequestParam("img") MultipartFile[] file,
      @RequestParam("username") String username,
      Model m) {
    for (MultipartFile multipartFile : file) {
      if (!multipartFile.isEmpty()) {
        try {
          multipartFile.transferTo(new File("D:\\" + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return "success";
  }
}
