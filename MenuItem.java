public class MenuItem {
  private String name;
  private Double price;
  private String desc;

  public MenuItem(String name, Double price, String desc) {
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
