import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  public void cancelMenuItem(String menuItemName) {
    this.menuItems = this.menuItems.stream().filter(v -> !v.getName().replaceAll("\\s+", "").equals(menuItemName.replaceAll("\\s+", ""))).collect(
        Collectors.toList());
  }
}
