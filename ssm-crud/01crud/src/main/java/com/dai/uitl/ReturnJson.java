package com.dai.uitl;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 通用的返回给客户端json字符串的类
 * @author: dai.jf
 * @date: 2021/8/31-10:53
 */
public class ReturnJson {

  /**
   * 状态码 100：成功 200：失败（自定义）
   */
  private int code;
  /**
   * 提示信息
   */
  private String msg;
  /**
   * 用户要返回给浏览器的信息都放在map中
   */
  private Map<String, Object> extendedMap = new HashMap<>();

  public static ReturnJson success() {
    ReturnJson result = new ReturnJson();
    result.setCode(100);
    result.setMsg("处理成功");
    return result;
  }

  public static ReturnJson fail() {
    ReturnJson result = new ReturnJson();
    result.setCode(200);
    result.setMsg("处理失败");
    return result;
  }

  /**
   * 用于携带pageInfo信息
   */
  public ReturnJson add(String key, Object value) {
    this.getExtendedMap().put(key, value);
    return this;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Map<String, Object> getExtendedMap() {
    return extendedMap;
  }

  public void setExtendedMap(Map<String, Object> extendedMap) {
    this.extendedMap = extendedMap;
  }
}
