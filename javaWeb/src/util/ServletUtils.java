package util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * created : 2021/8/8 2:42 description: 用于注入servlet的获取的请求参数
 *
 * @author Dai
 */
public class ServletUtils {

  /**
   * 把map中的值注入到对应的javaBean属性中
   *
   * @param value
   * @param bean
   */
  public static <T> T copyParamToBean(Map value, T bean) {
    System.out.println("参数注入前" + bean);
    try {
      /** 把所有请求的参数都注入到该对象中 */
      BeanUtils.populate(bean, value);
      System.out.println("参数注入后" + bean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bean;
  }

  /**
   * 将字符串转为int型数据，省去在每个servlet中进行转换和try catch
   *
   * @param str
   * @param defaultValue
   * @return
   */
  public static int parseInt(String str, int defaultValue) {
    try {
      return Integer.parseInt(str);
    } catch (Exception ignored) {
    }
    return defaultValue;
  }
}
