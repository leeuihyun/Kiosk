import java.util.List;

public class Menu {
  private String category;
  private List<MenuItem> menuItems;

  public Menu(String category, List<MenuItem> menuItems) {
    this.category = category;
    this.menuItems = menuItems;
  }

  public String getCategory() {
    return this.category;
  }

  public List<MenuItem> getMenuItems() {
    return this.menuItems;
  }
}
