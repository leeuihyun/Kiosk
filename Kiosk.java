import java.util.List;
import java.util.Scanner;

public class Kiosk {
  private List<MenuItem> menuItems;

  public Kiosk(List<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  public void start() {
    Scanner scan = new Scanner(System.in);

    System.out.println("SHAKESHACK MENU");

    for(int i=0;i<menuItems.size();i++) {
      System.out.println(String.format(
          "%d. %-15s | W %-3.1f | %s",
          (i + 1),
          menuItems.get(i).getName(),
          menuItems.get(i).getPrice(),
          menuItems.get(i).getDesc()
      ));
      if(i == menuItems.size()) {
        System.out.println("0. 종료");
      }
    }

    while(true) {
      System.out.print("메뉴 번호를 입력해주세요 : ");
      int inputNumber = scan.nextInt();

      if(inputNumber < 0) {
        System.out.println("올바르지 않은 메뉴 번호입니다.");
        continue;
      }

      if(inputNumber > menuItems.size()) {
        System.out.println("올바르지 않은 메뉴 번호입니다.");
        continue;
      }

      if(inputNumber == 0) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }

      System.out.println((inputNumber) + ". " + menuItems.get(inputNumber-1).getName() + "  | W " + menuItems.get(inputNumber-1).getPrice() + " | " + menuItems.get(inputNumber-1).getDesc());
    }
  }
}
