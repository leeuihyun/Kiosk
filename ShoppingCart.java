import java.util.ArrayList;
import java.util.List;

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

  public void cancelShoppingCart() {
    this.menuItems.clear();
  }

  public Double getTotalPrice() {
    return menuItems.stream().map(MenuItem::getPrice).reduce(0.0, Double::sum);
  }
}
