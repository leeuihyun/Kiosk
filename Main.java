import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    List<Menu> menuList = new ArrayList<>();

    menuList.add(new Menu("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
    menuList.add(new Menu("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
    menuList.add(new Menu("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
    menuList.add(new Menu("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

    System.out.println("SHAKESHACK MENU");

    for(int i=0;i<menuList.size();i++) {
      System.out.println(String.format(
          "%d. %-15s | W %-3.1f | %s",
          (i + 1),
          menuList.get(i).getName(),
          menuList.get(i).getPrice(),
          menuList.get(i).getDesc()
      ));
      if(i == menuList.size()) {
        System.out.println("0. 종료");
      }
    }
    while(true) {
      int inputNumber = scan.nextInt();

      if(inputNumber == 0) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }

      System.out.println((inputNumber) + ". " + menuList.get(inputNumber-1).getName() + "  | W " + menuList.get(inputNumber-1).getPrice() + " | " + menuList.get(inputNumber-1).getDesc());
    }
  }
}