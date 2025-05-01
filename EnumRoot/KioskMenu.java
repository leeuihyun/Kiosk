package EnumRoot;

/*
 * name : KioskMenu
 * desc : Kiosk 전체 카테고리 Enum
 */
public enum KioskMenu {
  BURGERS("Burgers"),
  DRINKS("Drinks"),
  DESSERTS("Desserts");

  private final String name;

  KioskMenu(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
