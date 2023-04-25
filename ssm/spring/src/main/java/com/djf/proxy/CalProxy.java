package com.djf.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: dai.jf
 * @date: 2021/8/18 22:26
 * @description: 生成代理对象的类
 */
public class CalProxy {
  /**
   * 为传入的参数创建一个代理对象
   *
   * @param cal: 被代理对象
   * @return
   */
  public static Calculate getProxy(Calculate cal) {
    // 方法执行器
    InvocationHandler handler =
        new InvocationHandler() {
          /**
           * @param proxy 代理对象.[不使用,给jdk使用]
           * @param method 当前将要执行的目标对象的方法
           * @param args 外界传入的值
           * @return
           * @throws Throwable
           */
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 利用反射执行目标方法,返回目标方法执行后的返回值
            Object result = null;
            try {
              // LogUtils.logStart(method, args);
              result = method.invoke(cal, args);
              // LogUtils.logReturn(method, result);
            } catch (Exception e) {
              // LogUtils.logException(method, e);
            } finally {
              // LogUtils.logEnd(method);
            }
            return result;
          }
        };
    // 被代理对象的类加载器
    ClassLoader classLoader = cal.getClass().getClassLoader();
    // cal对象实现的接口
    Class<?>[] interfaces = cal.getClass().getInterfaces();
    Object proxy = Proxy.newProxyInstance(classLoader, interfaces, handler);
    return (Calculate) proxy;
  }
}
