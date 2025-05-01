package Utils;

import EnumRoot.Loop;
import domain.Menu;
import domain.ShoppingCart;
import java.util.List;

/*
 * name : ExceptionUtils
 * desc : Kiosk 과제에서 사용되는 예외처리 클래스
 */
public class ExceptionUtils {
  private ExceptionUtils() {}

  // 기본 메뉴(카테고리) 검증
  public static Loop validateMenuNumber(Integer menuInputNumber, ShoppingCart shoppingCart, List<Menu> menus) {
    if(menuInputNumber == 0) {
      System.out.println("프로그램을 종료합니다.");
      return Loop.BREAK;
    }
    if(menuInputNumber < 0) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }
    if(shoppingCart.isEmptyShoppingCart() && menuInputNumber > menus.size()) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }
    if(!shoppingCart.isEmptyShoppingCart() && menuInputNumber > menus.size()+2) {
      System.out.println("올바르지 않은 번호입니다.");
      return Loop.CONTINUE;
    }
    return Loop.TRUE;
  }

  // 세부 메뉴(카테고리) 검증
  public static <T> Loop validateDetailMenuNumber(List<T> list, Integer number, String exitOrContinue) {
    if(number < 0) {
      System.out.println("입력하신 번호가 0보다 작습니다.");
      return Loop.CONTINUE;
    }

    if(number > list.size()) {
      System.out.println("입력하신 번호가 메뉴의 갯수보다 많습니다.");
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
}
