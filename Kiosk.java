import java.util.List;
import java.util.Scanner;

public class Kiosk {
  private List<Menu> menus;
  private Scanner scan = new Scanner(System.in);
  private ShoppingCart shoppingCart;

  public Kiosk(List<Menu> menus) {
    this.menus = menus;
    this.shoppingCart = new ShoppingCart();
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
      // 메인 메뉴 출력
      System.out.println("[ MAIN MENU ]");
      for(int i=0;i<menus.size();i++) {
        System.out.println((i+1) + ". " + menus.get(i).getCategory());
      }
      System.out.println("0. 종료");

      if(!shoppingCart.isEmptyShoppingCart()){
        System.out.println("[ ORDER MENU ]");
        System.out.println(String.format("%d. %-15s | %s", menus.size()+1, "Orders", "장바구니를 확인 후 주문합니다."));
        System.out.println(String.format("%d. %-15s | %s", menus.size()+2, "Cancel", "진행중인 주문을 취소합니다."));
      }

      // 메뉴 번호 입력 및 검증
      System.out.print("메뉴 번호를 입력해주세요 : ");
      int inputNumber = scan.nextInt();
      if(inputNumber < 0) {
        System.out.println("올바르지 않은 번호입니다.");
        continue;
      }
      if(shoppingCart.isEmptyShoppingCart() && inputNumber > menus.size()) {
        System.out.println("올바르지 않은 번호입니다.");
        continue;
      }
      if(!shoppingCart.isEmptyShoppingCart() && inputNumber > menus.size()+2) {
        System.out.println("올바르지 않은 번호입니다.");
        continue;
      }
      if(inputNumber == 0) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }

      // 메뉴(카테고리) 번호 입력에 따른 카테고리별 세부메뉴 출력
      if(inputNumber <= menus.size()) {
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
      }else {
        if(inputNumber == menus.size()+1) {
          System.out.println("아래와 같이 주문하시겠습니까?");
          System.out.println();

          System.out.println("[ ORDERS ]");
          for(int i=0;i<shoppingCart.getShoppingCart().size();i++) {
            System.out.println(String.format(
                "%-15s | W %-3.1f | %s",
                shoppingCart.getShoppingCart().get(i).getName(),
                shoppingCart.getShoppingCart().get(i).getPrice(),
                shoppingCart.getShoppingCart().get(i).getDesc()));
          }
          System.out.println("[ TOTAL ]");
          System.out.println("W " + shoppingCart.getTotalPrice());
          System.out.println();

          System.out.println("1. 주문        2. 메뉴판");
          if(scan.nextInt() == 1) {
            System.out.println("주문이 완료되었습니다. 금액은 W " + shoppingCart.getTotalPrice()+"입니다.");
            System.out.println("종료합니다.");
            break;
          }else {
            continue;
          }
        }else {
          System.out.println("주문을 전부 취소하시겠습니까?");
          System.out.println("1. 확인        2. 취소");
          if(scan.nextInt() == 1) {
            shoppingCart.cancelShoppingCart();
            System.out.println("주문이 전부 취소되었습니다. 초기화면으로 이동합니다.");
            continue;
          }
        }
      }

      // 세부 메뉴 입력
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

      // 장바구니 로직
      System.out.println("\"" + menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getName() +
          "  | W " + menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getPrice() +
          " | " + menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getDesc() + "\"");
      System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
      System.out.println("1. 확인        2. 취소");
      int shoppingNumber = scan.nextInt();

      if(shoppingNumber == 1) {
        shoppingCart.addShoppingCart(menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1));
        System.out.println(menus.get(inputNumber-1).getMenuItems().get(inputDetailMenuNumber-1).getName() + " 이 장바구니에 추가되었습니다.");
      }
    }
  }
}
