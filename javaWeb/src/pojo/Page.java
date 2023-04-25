package pojo;

import java.util.List;

/**
 * @author: dai.jf
 * @date: 2021/8/9 18:45
 * @description: page是分页的模型对象
 * @param <T> :是具体模块的javaB对象
 */
public class Page<T> {

  public static Integer PAGE_SIZE = 4;

  /** 当前页 */
  private Integer pageCurrent;

  /** 总页数 */
  private Integer pageTotal;

  /** 每页显示的数量 */
  private Integer pageSize = PAGE_SIZE;

  /** 总记录数 */
  private Integer pageTotalCount;

  /** 每页显示数据 */
  private List<T> items;

  /**
   * 分页条的请求地址
   * @return
   */
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getPageCurrent() {
    return pageCurrent;
  }

  public void setPageCurrent(Integer pageCurrent) {
    this.pageCurrent = pageCurrent;
  }

  public Integer getPageTotal() {
    return pageTotal;
  }

  public void setPageTotal(Integer pageTotal) {
    this.pageTotal = pageTotal;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPageTotalCount() {
    return pageTotalCount;
  }

  public void setPageTotalCount(Integer pageTotalCount) {
    this.pageTotalCount = pageTotalCount;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "Page{" +
            "pageCurrent=" + pageCurrent +
            ", pageTotal=" + pageTotal +
            ", pageSize=" + pageSize +
            ", pageTotalCount=" + pageTotalCount +
            ", items=" + items +
            ", url='" + url + '\'' +
            '}';
  }
}
