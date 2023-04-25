package com.djf.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: dai.jf
 * @date: 2021/8/18 23:24
 * @description: 如何将这个类（切面类）中的这些方法（通知方法）动态的在目标方法运行的各个位置切入
 *     <p>Aspect: 一旦有切面切入,为了让其内的内容运行,就会创建代理对象
 *     <p>固定格式： execution(访问权限符 返回值类型 方法全类名(参数表))
 */
@Aspect
@Component
public class LogUtils {

  @Before("execution(* com.djf..*(..))")
  public static void logStart(JoinPoint point) {
    // 获取目标方法使用的参数
    Object[] args = point.getArgs();
    // 获取方法签名
    Signature signature = point.getSignature();
    String name = signature.getName();
    System.out.println("【" + name + "】方法开始执行，用的参数列表【" + Arrays.asList(args) + "】");
  }

  // 告诉Spring这个result用来接收返回值：
  //	returning="result"；
  @AfterReturning(value = "execution(* com.djf..*(..))", returning = "result")
  public static void logReturn(JoinPoint point, Object result) {
    // 获取方法签名
    Signature signature = point.getSignature();
    String name = signature.getName();
    System.out.println("【" + name + "】方法正常执行完成，计算结果是：" + result);
  }

  @AfterThrowing(value = "execution(* com.djf..*(..))", throwing = "e")
  public static void logException(JoinPoint point, Exception e) {
    // 获取方法签名
    Signature signature = point.getSignature();
    String name = signature.getName();
    System.out.println("【" + name + "】方法执行出现异常了，异常信息是：" + e + "；这个异常已经通知测试小组进行排查");
  }

  @After("execution(* com.djf..*(..))")
  public static void logEnd(JoinPoint point) {
    // 获取方法签名
    Signature signature = point.getSignature();
    String name = signature.getName();
    System.out.println("【" + name + "】方法最终结束了");
  }

  @Around("execution(* com.djf.*(..))")
  public Object test(ProceedingJoinPoint pjp) {
    Object[] args = pjp.getArgs();
    Object proceed = null;
    try {
      // 就是利用反射调用目标对象 ==>等同于 method.invoke(obj,args);[环绕通知类似动态代理全过程]
      proceed = pjp.proceed(args);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return proceed;
  }
}
