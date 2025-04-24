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

  private Loop validateMenuNumber(Integer MenuInputNumber) {
    if(MenuInputNumber == 0) {
      System.out.println("프로그램을 종료합니다.");
      return Loop.BREAK;
    }
    if(MenuInputNumber < 0) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }
    if(shoppingCart.isEmptyShoppingCart() && MenuInputNumber > menus.size()) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }
    if(!shoppingCart.isEmptyShoppingCart() && MenuInputNumber > menus.size()+2) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }
    return Loop.TRUE;
  }

  private <T> Loop checkNumber(List<T> list, Integer number, String exitOrContinue) {
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

  private void printShoppingCartMenuItem() {
    for(int i=0;i<this.shoppingCart.getShoppingCart().size();i++) {
      System.out.printf(
          "%-15s | W %-3.1f | %s\n",
          this.shoppingCart.getShoppingCart().get(i).getName(),
          this.shoppingCart.getShoppingCart().get(i).getPrice(),
          this.shoppingCart.getShoppingCart().get(i).getDesc());
    }
  }

  private void addShoppingCartAndPrint(Integer ShoppingNumber, MenuItem selectedMenuItem){
    if(ShoppingNumber == 1) {
      shoppingCart.addShoppingCart(selectedMenuItem);
      System.out.println(selectedMenuItem.getName() + " 이 장바구니에 추가되었습니다.");
    }
  }
  private void Orders() {
    System.out.println("아래와 같이 주문하시겠습니까?\n");
    System.out.println("[ ORDERS ]");
  }

  private void printMainMenu() {
    System.out.println("[ MAIN MENU ]");
    for(int i=0;i<menus.size();i++) {
      System.out.println((i+1) + ". " + menus.get(i).getCategory());
    }
    System.out.println("0. 종료");

    if(!shoppingCart.isEmptyShoppingCart()){
      System.out.println("[ ORDER MENU ]");
      System.out.printf("%d. %-15s | %s\n", (menus.size()+1), "Orders", "장바구니를 확인 후 주문합니다.");
      System.out.printf("%d. %-15s | %s\n", (menus.size()+2), "Cancel", "진행중인 주문을 취소합니다.");
    }
  }

  private Integer getNumber(String text) {
    System.out.printf(text);
    return scan.nextInt();
  }

  private void printCategoryMenu(Menu menu) {
    System.out.println("[ " + menu.getCategory().toUpperCase() + " ]");
    List<MenuItem> items = menu.getMenuItems();
    for (int i = 0; i < items.size(); i++) {
      MenuItem item = items.get(i);
      System.out.printf("%d. %-15s | W %-3.1f | %s%n", i + 1, item.getName(), item.getPrice(), item.getDesc());
    }
    System.out.println("0. 뒤로가기");
  }

  private void printSelectedMenuItem(MenuItem selectedMenuItem) {
    System.out.println("선택한 메뉴: " + selectedMenuItem.getName() +
        "  | W " + selectedMenuItem.getPrice() +
        " | " + selectedMenuItem.getDesc());

    System.out.println("\"" + selectedMenuItem.getName() +
        "  | W " + selectedMenuItem.getPrice() +
        " | " + selectedMenuItem.getDesc() + "\"");
  }

  private void cancelMenuItemsAndPrint(Integer cancelNumber) {
    if(cancelNumber == 1) {
      this.shoppingCart.clearShoppingCart();
      System.out.println("주문이 전부 취소되었습니다. 초기화면으로 이동합니다.");
    }
  }
  public void start() {
    while(true) {
      // 메인 메뉴 출력
      printMainMenu();

      // 메뉴 번호 입력 및 검증
      int inputNumber = getNumber("메뉴 번호를 입력해주세요 : ");
      if(validateMenuNumber(inputNumber) == Loop.CONTINUE) {
        continue;
      }else if(validateMenuNumber(inputNumber) == Loop.BREAK){
        break;
      }

      // 메뉴(카테고리) 번호 입력에 따른 카테고리별 세부메뉴 출력
      if(inputNumber <= menus.size()) {
        Menu selectedMenu = menus.get(inputNumber - 1);
        printCategoryMenu(selectedMenu);
        int inputDetailMenuNumber = getNumber("세부 메뉴 번호를 입력해주세요 : ");
        Loop checkInputDetailMenuNumber = checkNumber(menus.get(inputNumber-1).getMenuItems(), inputDetailMenuNumber, "continue");
        if(checkInputDetailMenuNumber == Loop.CONTINUE) {
          continue;
        }
        if(checkInputDetailMenuNumber == Loop.BREAK){
          break;
        }

        MenuItem selectedMenuItem = selectedMenu.getMenuItems().get(inputDetailMenuNumber-1);
        printSelectedMenuItem(selectedMenuItem);

        int shoppingNumber = getNumber("위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인        2. 취소\n");
        addShoppingCartAndPrint(shoppingNumber,selectedMenuItem);
      }else {
        // 주문 선택
        if(inputNumber == menus.size()+1) {
          Orders();
          printShoppingCartMenuItem();
          int orderNumber = getNumber("[ TOTAL ]\nW "+ this.shoppingCart.getTotalPrice() + "\n1. 주문        2. 메뉴판\n");
          if(orderNumber == 1) {
            System.out.println("주문이 완료되었습니다. 금액은 W " + this.shoppingCart.getTotalPrice()+"입니다.");
            shoppingCart.clearShoppingCart();
          }
        }else {
          // 주문 취소 선택
          int cancelNumber = getNumber("주문을 전부 취소하시겠습니까?\n1. 확인        2. 취소\n");
          cancelMenuItemsAndPrint(cancelNumber);
        }
      }
    }
  }
}
