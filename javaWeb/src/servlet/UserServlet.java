package servlet;

import com.google.gson.Gson;
import pojo.User;
import service.impl.UserServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * created : 2021/8/8 1:29 description:合并登录注册servlet
 *
 * @author Dai
 */
@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

  private static final long serialVersionUID = -7870120610113778481L;
  UserServiceImpl userService = new UserServiceImpl();

  /**
   * 登录实现
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void login(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1、取请求参数
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    // 2、调用service处理业务
    User userLogin = userService.login(new User(null, username, password, null));
    if (userLogin == null) {
      // 把错误信息和 回显的表单信息 存放之request中
      request.setAttribute("msg", "用户名或密码错误");
      request.setAttribute("username", username);
      // 登录失败
      request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
    } else {
      // 登录成功
      // 保存用户登录后的信息
      request.getSession().setAttribute("user", userLogin);
      request.getRequestDispatcher("pages/user/login_success.jsp").forward(request, response);
    }
  }

  /**
   * 注册实现
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void register(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1、获取请求参数
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String code = request.getParameter("code");

    // 参数注入
    User user = ServletUtils.copyParamToBean(request.getParameterMap(), new User());

    // 2、判断验证码是否正确
    // 获取session中的验证码
    String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
    // 删除验证码【阻止重复提交】
    request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

    if (token != null && token.equalsIgnoreCase(token)) {
      // 正确，再判断用户名是否可用

      // 已存在，不可用
      if (userService.exitUsername(username)) {
        System.out.println("用户名【" + username + "】已存在");
        /// 把错误信息和 回显的表单信息 存放之request中
        request.setAttribute("msg", "用户名已存在！！");
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
      } else {
        System.out.println("注册成功");
        userService.register(new User(null, username, password, email));
        request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
      }
    } else {
      // 不正确
      System.out.println("验证码【" + code + "】填写错误");
      /// 把错误信息和 回显的表单信息 存放之request中
      request.setAttribute("msg", "验证码错误");
      request.setAttribute("username", username);
      request.setAttribute("email", email);
      request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
    }
  }

  /**
   * 注销实现
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void logout(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 销毁session中的用户登录信息【或销毁session】
    request.getSession().invalidate();
    // 重定向到首页
    response.sendRedirect(request.getContextPath());
  }

  /**
   * 验证用户名是否存在
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void existUsername(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 获取请求参数username
    String username = request.getParameter("username");

    // 调用service 的 方法
    boolean exitUsername = userService.exitUsername(username);

    // 把返回的结果封装成map集合
    Map<String, Object> map = new HashMap<>();
    map.put("exitUsername", exitUsername);

    Gson gson = new Gson();
    String json = gson.toJson(map);

    response.getWriter().write(json);
  }
}
