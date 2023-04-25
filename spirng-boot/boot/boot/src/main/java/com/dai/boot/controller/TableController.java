package com.dai.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dai.boot.entity.User;
import com.dai.boot.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author dai.jf
 * @date 2021/10/27-9:50
 * @description:
 */
@Controller
public class TableController {

  @Autowired
  UserService userService;


  /**
   * RedirectAttributes:重定向携带数据
   */
  @GetMapping("/user/delete/{id}")
  public String deleteUser(@PathVariable String id,
      @RequestParam(value = "pn", defaultValue = "1") Integer pn, RedirectAttributes ra) {
    userService.removeById(id);
    ra.addAttribute("pn", pn);
    return "redirect:/dynamic_table";
  }


  @GetMapping("basic_table")
  public String basicTable() {
    return "table/basic_table";
  }

  @GetMapping("dynamic_table")
  public String dynamicTable(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
      Model model) {
    //分页查询数据
    Page<User> userPage = new Page<>(pn, 3);
    //分页查询的结果
    Page<User> page = userService.page(userPage, null);
    model.addAttribute("page", page);
    //查出数据库的数据记录
    List<User> records = page.getRecords();
    return "table/dynamic_table";
  }

  @GetMapping("responsive_table")
  public String responsiveTable() {
    return "table/responsive_table";
  }

  @GetMapping("editable_table")
  public String editableTable() {
    return "table/editable_table";
  }
}
