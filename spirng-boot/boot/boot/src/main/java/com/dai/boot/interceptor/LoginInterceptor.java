package com.dai.boot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dai.jf
 * @date 2021/10/27-11:07
 * @description:
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  /**
   * 目标方法执行之前
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String requestUri = request.getRequestURI();
    log.info("preHandle拦截的请求路径是{}", requestUri);

    //登录检查逻辑
    HttpSession session = request.getSession();

    Object loginUser = session.getAttribute("loginUser");

    if (loginUser != null) {
      //放行
      return true;
    }

    //拦截住。未登录。跳转到登录页
    request.setAttribute("msg", "请先登录");
//        re.sendRedirect("/");
    request.getRequestDispatcher("/").forward(request, response);
    return false;
  }

  /**
   * 目标方法执行完成以后
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    log.info("postHandle执行{}", modelAndView);
  }

  /**
   * 页面渲染以后
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    log.info("afterCompletion执行异常{}", ex);
  }
}
