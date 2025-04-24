import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    List<MenuItem> burgerItems = new ArrayList<>();
    List<MenuItem> drinkItems = new ArrayList<>();
    List<MenuItem> dessertItems = new ArrayList<>();

    burgerItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
    burgerItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
    burgerItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
    burgerItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

    drinkItems.add(new MenuItem("Coke", 2.0, "시원한 콜라"));
    drinkItems.add(new MenuItem("Lemonade", 2.5, "시원한 레몬에이드"));
    drinkItems.add(new MenuItem("Iced Tea", 2.3, "시원한 아이스티"));

    dessertItems.add(new MenuItem("Chocolate", 3.0, "맛있는 초콜릿"));
    dessertItems.add(new MenuItem("Cake", 4.5, "맛있는 케이크"));
    dessertItems.add(new MenuItem("Cookie", 1.5, "맛있는 쿠키"));

    Menu burgerMenus = new Menu("Burgers", burgerItems);
    Menu drinkMenus = new Menu("Drinks", drinkItems);
    Menu dessertMenus = new Menu("Desserts", dessertItems);

    Kiosk kiosk = new Kiosk(new ArrayList<>(Arrays.asList(burgerMenus, drinkMenus, dessertMenus)));
    kiosk.start();
  }
}