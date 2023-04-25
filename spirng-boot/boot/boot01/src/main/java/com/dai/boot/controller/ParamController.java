package com.dai.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dai.jf
 * @date 2021/10/16-19:52
 * @description:
 */
@RestController
public class ParamController {

  @GetMapping("/car/{id}/owner/{username}")
  public Map handle01(@PathVariable("id") int id, @PathVariable("username") String name) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("name", name);
    return map;
  }

  @GetMapping("/person")
  public Map handle02(@RequestParam("age") int age) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("age", age);
    return map;
  }

  @GetMapping("/in")
  public Map handle03(@RequestParam("interest") List<String> interest) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("interest", interest);
    return map;
  }
}
