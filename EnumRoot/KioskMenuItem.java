package EnumRoot;

/*
 * name : KioskMenuItem
 * desc : KioskMenuItem info enum
 */
public enum KioskMenuItem {
  SHACKBURGER("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
  SMOKESHACK("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
  CHEESEBURGER("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
  HAMBURGER("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"),

  COKE("Coke", 2.0, "시원한 콜라"),
  LEMONADE("Lemonade", 2.5, "시원한 레몬에이드"),
  ICEDTEA("Iced Tea", 2.3, "시원한 아이스티"),

  CHOCOLATE("Chocolate", 3.0, "맛있는 초콜릿"),
  CAKE("Cake", 4.5, "맛있는 케이크"),
  COOKIE("Cookie", 1.5, "맛있는 쿠키");

  private final String name;
  private final Double price;
  private final String desc;

  KioskMenuItem(String name, Double price, String desc) {
    this.name = name;
    this.price = price;
    this.desc = desc;
  }

  public String getName() {
    return this.name;
  }

  public Double getPrice() {
    return this.price;
  }

  public String getDesc() {
    return this.desc;
  }
}
