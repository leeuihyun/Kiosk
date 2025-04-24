import java.util.List;
import java.util.Scanner;

public class Kiosk {
  private List<Menu> menus;
  private Scanner scan = new Scanner(System.in);

  public Kiosk(List<Menu> menus) {
    this.menus = menus;
  }

  public <T> Loop checkNumber(List<T> list, Integer number, String exitOrContinue) {
    if(number < 0) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }

    if(number > list.size()) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }

    if(number == 0) {

      if(exitOrContinue.equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        return Loop.BREAK;
      }else{
        System.out.println("재실행합니다.");
        return Loop.CONTINUE;
      }
    }

    return Loop.TRUE;
  }

  public void start() {
    while(true) {
      System.out.println("[ MAIN MENU ]");
      for(int i=0;i<menus.size();i++) {
        System.out.println((i+1) + ". " + menus.get(i).getCategory());
      }
      System.out.println("0. 종료");

      System.out.print("메뉴 번호를 입력해주세요 : ");
      int inputNumber = scan.nextInt();
      Loop checkInputNumber = checkNumber(menus, inputNumber, "exit");
      if(checkInputNumber == Loop.CONTINUE) {
        continue;
      }
      if(checkInputNumber == Loop.BREAK){
        break;
      }

      System.out.println("[ " + menus.get(inputNumber-1).getCategory().toUpperCase() + " ]");
      for(int i=0;i<menus.get(inputNumber-1).getMenuItems().size();i++) {
        System.out.println(String.format(
            "%d. %-15s | W %-3.1f | %s",
            (i + 1),
            menus.get(inputNumber-1).getMenuItems().get(i).getName(),
            menus.get(inputNumber-1).getMenuItems().get(i).getPrice(),
            menus.get(inputNumber-1).getMenuItems().get(i).getDesc()
        ));
      }
      System.out.println("0. 뒤로가기");
      System.out.print("세부 메뉴 번호를 입력해주세요 : ");
      int inputDetailMenuNumber = scan.nextInt();
      Loop checkInputDetailMenuNumber = checkNumber(menus.get(inputNumber-1).getMenuItems(), inputDetailMenuNumber, "continue");
      if(checkInputDetailMenuNumber == Loop.CONTINUE) {
        continue;
      }
      if(checkInputDetailMenuNumber == Loop.BREAK){
        break;
      }
      System.out.println("선택한 메뉴: " + menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getName() +
          "  | W " + menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getPrice() +
          " | " + menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getDesc());
    }
  }
}
