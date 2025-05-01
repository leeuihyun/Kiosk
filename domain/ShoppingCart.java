package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * name : ShoppingCart
 * desc : 장바구니 클래스
 */
public class ShoppingCart {
  private List<MenuItem> menuItems;

  public ShoppingCart() {
    this.menuItems = new ArrayList<>();
  }

  public void addShoppingCart(MenuItem menuItem) {
    this.menuItems.add(menuItem);
  }

  public List<MenuItem> getShoppingCart() {
    return this.menuItems;
  }

  public boolean isEmptyShoppingCart() {
    return menuItems.isEmpty();
  }

  public void clearShoppingCart() {
    this.menuItems.clear();
  }

  public Double getTotalPrice(Integer rate) {
    Double originalTotalPrice = menuItems.stream().map(MenuItem::getPrice).reduce(0.0, Double::sum);
    return originalTotalPrice - (originalTotalPrice * rate/100.0);
  }

  // 장바구니 메뉴 취소 기능
  public void cancelMenuItem(String menuItemName) {
    this.menuItems = this.menuItems.stream().filter(v -> !v.getName().replaceAll("\\s+", "").equals(menuItemName.replaceAll("\\s+", ""))).collect(
        Collectors.toList());
  }

  // 장바구니 포함 검사
  public boolean containsShoppingCart(String menuItemName) {
    return this.menuItems.stream().anyMatch(v -> v.getName().replaceAll("\\s","").equals(menuItemName.replaceAll("\\s+", "")));
  }
}
