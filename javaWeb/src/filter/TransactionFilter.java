package filter;

import util.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: dai.jf
 * @date: 2021/8/16 11:35
 * @description:
 */
@WebFilter(filterName = "TransactionFilter", urlPatterns = "/*")
public class TransactionFilter implements Filter {
  @Override
  public void destroy() {}

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    try {
      chain.doFilter(req, resp);
      // 事务提交
      JdbcUtils.commitAndClose();
    } catch (Exception e) {
      // 事务回滚
      JdbcUtils.rollbackAndClose();
      e.printStackTrace();
      // 把异常抛给tomcat统一友好的错误提示
      throw new RuntimeException(e);
    }
  }

  @Override
  public void init(FilterConfig config) throws ServletException {}
}
