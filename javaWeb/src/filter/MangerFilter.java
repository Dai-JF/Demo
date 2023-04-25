package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: dai.jf
 * @date: 2021/8/15 13:23
 * @description:
 */
@WebFilter(filterName = "MangerFilter",urlPatterns = "/pages/manager/*" )
public class MangerFilter implements Filter {
  @Override
  public void destroy() {}

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {

    HttpServletRequest servletRequest = (HttpServletRequest) req;

    Object user = servletRequest.getSession().getAttribute("user");

    // 未登录
    if (user == null) {
      servletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
    }
    // 跳转至资目标源路
    chain.doFilter(req, resp);
  }

  @Override
  public void init(FilterConfig config) throws ServletException {}
}
