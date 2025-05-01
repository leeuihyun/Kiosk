package domain;

import java.util.List;

/*
 * name : Menu
 * desc : 메뉴명 및 세부 메뉴 아이템을 관리하는 메뉴 클래스
 */
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
