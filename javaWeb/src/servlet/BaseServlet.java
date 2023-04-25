package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/** @author Dai */
@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public abstract class BaseServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 解决post请求乱码
    request.setCharacterEncoding("utf-8");

    // 解决响应中文乱码问题
    response.setContentType("text/html;charset=UTF-8");

    String action = request.getParameter("action");

    //        if (action.equals("login")) {//处理登录请求
    //            login(request, response);
    //        } else if (action.equals("register")) {//处理注册请求
    //            register(request, response);
    //        }

    // UserServlet.class.getDeclaredMethod(action);

    // 通过反射 动态获取相应业务方法并调用
    try {
      Method method =
          this.getClass()
              .getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

      // 设置private也能用
      method.setAccessible(true);
      // 调用方法
      method.invoke(this, request, response);

    } catch (Exception e) {
      e.printStackTrace();
      // 把异常抛给事务处理filter
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }
}
