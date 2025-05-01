package domain;

import static Utils.ExceptionUtils.validateCancelString;
import static Utils.ExceptionUtils.validateDetailMenuNumber;
import static Utils.ExceptionUtils.validateMenuNumber;

import EnumRoot.DiscountRate;
import EnumRoot.Loop;
import java.util.List;
import java.util.Scanner;

/*
 * name : Kiosk
 * desc : 메뉴를 관리하고 사용자 입력을 처리하는 클래스
 */
public class Kiosk {
  private List<Menu> menus;
  private Scanner scan;
  private ShoppingCart shoppingCart;

  public Kiosk(List<Menu> menus) {
    this.menus = menus;
    this.shoppingCart = new ShoppingCart();
    this.scan = new Scanner(System.in);
  }

  // 장바구니 목록 출력
  private void printShoppingCartMenuItem() {
    this.shoppingCart.getShoppingCart().stream().forEach(item -> System.out.printf("%-15s | W %-3.1f | %s\n", item.getName(), item.getPrice(), item.getDesc()));
  }

  // 장바구니 추가 메서드
  private void addShoppingCart(Integer ShoppingNumber, MenuItem selectedMenuItem){
    if(ShoppingNumber == 1) {
      shoppingCart.addShoppingCart(selectedMenuItem);
      System.out.println(selectedMenuItem.getName() + " 이 장바구니에 추가되었습니다.");
    }
  }

  // 주문 질문 출력 메서드
  private void orders() {
    System.out.println("아래와 같이 주문하시겠습니까?\n");
    System.out.println("[ ORDERS ]");
  }

  // 할인 정보 적용 후 주문완료 메서드
  private void orderWithDiscount() {
    System.out.println("할인 정보를 입력해주세요.");
    int index = 1;
    for(DiscountRate dr : DiscountRate.values()) {
      System.out.println((index++) + ". " + dr.getName() + " : " + dr.getRate()+"%");
    }
    DiscountRate discountInfo = DiscountRate.idCheck(Integer.parseInt(scan.nextLine()));
    System.out.println(discountInfo.getName() +"을 선택하셨습니다.");

    System.out.println("주문이 완료되었습니다. 금액은 W " + this.shoppingCart.getTotalPrice(discountInfo.getRate())+"입니다.");
    shoppingCart.clearShoppingCart();
  }

  // 메인 메뉴 출력 메서드
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

  // 문자열 및 정수 입출력 메서드
  private Integer getNumber(String text) {
    System.out.printf(text);
    return Integer.parseInt(scan.nextLine());
  }

  // 선택한 메뉴의 세부 메뉴 출력 메서드
  private void printDetailMenu(Menu menu) {
    System.out.println("[ " + menu.getCategory().toUpperCase() + " ]");
    List<MenuItem> items = menu.getMenuItems();
    for (int i = 0; i < items.size(); i++) {
      MenuItem item = items.get(i);
      System.out.printf("%d. %-15s | W %-3.1f | %s%n", i + 1, item.getName(), item.getPrice(), item.getDesc());
    }
    System.out.println("0. 뒤로가기");
  }

  // 선택한 메뉴 출력 메서드
  private void printSelectedMenuItem(MenuItem selectedMenuItem) {
    System.out.println("선택한 메뉴: " + selectedMenuItem.getName() +
        "  | W " + selectedMenuItem.getPrice() +
        " | " + selectedMenuItem.getDesc());

    System.out.println("\"" + selectedMenuItem.getName() +
        "  | W " + selectedMenuItem.getPrice() +
        " | " + selectedMenuItem.getDesc() + "\"");
  }

  // 주문 취소 메서드
  private void cancelMenuItems(String cancelMenuItemName) {
    this.shoppingCart.cancelMenuItem(cancelMenuItemName);
    System.out.println("주문이 취소되었습니다.");
    if(this.shoppingCart.isEmptyShoppingCart()) {
      System.out.println("장바구니가 비었습니다");
    }else {
      System.out.println("[ ORDERS ]");
      printShoppingCartMenuItem();
    }
  }

  // Kiost 전체 로직
  public void start() {
    while(true) {
      // 메인 메뉴 출력
      printMainMenu();

      // 메뉴 번호 입력 및 검증
      int inputNumber = getNumber("메뉴 번호를 입력해주세요 : ");
      Loop validateMenuNumber = validateMenuNumber(inputNumber,this.shoppingCart, menus);
      if(validateMenuNumber == Loop.CONTINUE) {
        continue;
      }else if(validateMenuNumber == Loop.BREAK){
        break;
      }

      // 메뉴(카테고리) 번호 입력에 따른 카테고리별 세부메뉴 출력 및 검증
      if(inputNumber <= menus.size()) {
        Menu selectedMenu = menus.get(inputNumber - 1);
        printDetailMenu(selectedMenu);
        int inputDetailMenuNumber = getNumber("세부 메뉴 번호를 입력해주세요 : ");
        Loop checkInputDetailMenuNumber = validateDetailMenuNumber(menus.get(inputNumber-1).getMenuItems(), inputDetailMenuNumber, "continue");
        if(checkInputDetailMenuNumber == Loop.CONTINUE) {
          continue;
        }
        if(checkInputDetailMenuNumber == Loop.BREAK){
          break;
        }

        MenuItem selectedMenuItem = selectedMenu.getMenuItems().get(inputDetailMenuNumber-1);
        printSelectedMenuItem(selectedMenuItem);

        int shoppingNumber = getNumber("위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인        2. 취소\n");
        addShoppingCart(shoppingNumber,selectedMenuItem);
      }else {
        // 주문 선택
        if(inputNumber == menus.size()+1) {
          orders();
          printShoppingCartMenuItem();
          int orderNumber = getNumber("[ TOTAL ]\nW "+ this.shoppingCart.getTotalPrice(0) + "\n1. 주문        2. 메뉴판\n");

          // 할인율 적용 주문
          if(orderNumber == 1) {
            orderWithDiscount();
          }
        }else {
          // 주문 취소 선택
          printShoppingCartMenuItem();
          System.out.println("어떤 주문을 취소하시겠습니까? 취소하실 메뉴의 이름을 입력해주세요.");
          String cancelMenuItemName = scan.nextLine();
          Loop cancelVaildateNumber = validateCancelString(shoppingCart, cancelMenuItemName);
          if(cancelVaildateNumber != Loop.CONTINUE) {
            cancelMenuItems(cancelMenuItemName);
          }
        }
      }
    }
  }
}
