package pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: dai.jf
 * @date: 2021/8/11 23:19
 * @description: 购物车对象
 */
public class Cart {
  /**
   * key是商品id。
   *
   * <p>value是商品信息
   */
  private HashMap<Integer, CartItem> items = new LinkedHashMap<>();

  /** 添加商品项 */
  public void addItem(CartItem cartItem) {
    // 查看是否已经添加过该商品
    CartItem item = items.get(cartItem.getId());
    if (item == null) {
      // 未添加，直接放入集合
      items.put(cartItem.getId(), cartItem);
    } else {
      // 已经添加，数量累加，总金额更新
      item.setCount(item.getCount() + 1);
      // multiply()中不能直接写item.getCount()，因为不接收integer类型
      item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
    }
  }

  /** 删除商品项 */
  public void deleteItem(Integer id) {
    items.remove(id);
  }

  /** 清空购物车 */
  public void clear() {
    items.clear();
  }

  /** 修改商品数量 */
  public void updateCount(Integer id, Integer count) {
    // 先看购物车中是否有此商品，如果有修改商品数量，更新总金额
    CartItem item = items.get(id);
    if (item != null) {
      // 修改商品数量，更新总金额
      item.setCount(count);
      item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
    }
  }

  public Integer getTotalCount() {

    Integer totalCount = 0;
    // 遍历map集合中的每个键值对（entry）
    for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
      totalCount += entry.getValue().getCount();
    }
    return totalCount;
  }

  public BigDecimal getTotalPrice() {
    BigDecimal totalPrice = new BigDecimal(0);
    for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
      totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
    }
    return totalPrice;
  }

  public Cart() {}

  public HashMap<Integer, CartItem> getItems() {
    return items;
  }

  public void setItems(HashMap<Integer, CartItem> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "Cart{"
        + "totalCount="
        + getTotalCount()
        + ", totalPrice="
        + getTotalPrice()
        + ", items="
        + items
        + '}';
  }
}
