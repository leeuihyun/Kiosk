import EnumRoot.KioskMenu;
import EnumRoot.KioskMenuItem;
import domain.Kiosk;
import domain.Menu;
import domain.MenuItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<MenuItem> burgerItems = new ArrayList<>();
    List<MenuItem> drinkItems = new ArrayList<>();
    List<MenuItem> dessertItems = new ArrayList<>();

    burgerItems.add(new MenuItem(KioskMenuItem.SHACKBURGER.getName(), KioskMenuItem.SHACKBURGER.getPrice(), KioskMenuItem.SHACKBURGER.getDesc()));
    burgerItems.add(new MenuItem(KioskMenuItem.SMOKESHACK.getName(), KioskMenuItem.SMOKESHACK.getPrice(), KioskMenuItem.SMOKESHACK.getDesc()));
    burgerItems.add(new MenuItem(KioskMenuItem.CHEESEBURGER.getName(), KioskMenuItem.CHEESEBURGER.getPrice(), KioskMenuItem.CHEESEBURGER.getDesc()));
    burgerItems.add(new MenuItem(KioskMenuItem.HAMBURGER.getName(), KioskMenuItem.HAMBURGER.getPrice(), KioskMenuItem.HAMBURGER.getDesc()));

    drinkItems.add(new MenuItem(KioskMenuItem.COKE.getName(), KioskMenuItem.COKE.getPrice(), KioskMenuItem.COKE.getDesc()));
    drinkItems.add(new MenuItem(KioskMenuItem.LEMONADE.getName(), KioskMenuItem.LEMONADE.getPrice(), KioskMenuItem.LEMONADE.getDesc()));
    drinkItems.add(new MenuItem(KioskMenuItem.ICEDTEA.getName(), KioskMenuItem.ICEDTEA.getPrice(), KioskMenuItem.ICEDTEA.getDesc()));

    dessertItems.add(new MenuItem(KioskMenuItem.CHOCOLATE.getName(), KioskMenuItem.CHOCOLATE.getPrice(), KioskMenuItem.CHOCOLATE.getDesc()));
    dessertItems.add(new MenuItem(KioskMenuItem.CAKE.getName(), KioskMenuItem.CAKE.getPrice(), KioskMenuItem.CAKE.getDesc()));
    dessertItems.add(new MenuItem(KioskMenuItem.COOKIE.getName(), KioskMenuItem.COOKIE.getPrice(), KioskMenuItem.COOKIE.getDesc()));

    Menu burgerMenus = new Menu(KioskMenu.BURGERS.getName(), burgerItems);
    Menu drinkMenus = new Menu(KioskMenu.DRINKS.getName(), drinkItems);
    Menu dessertMenus = new Menu(KioskMenu.DESSERTS.getName(), dessertItems);

    Kiosk kiosk = new Kiosk(new ArrayList<>(Arrays.asList(burgerMenus, drinkMenus, dessertMenus)));
    kiosk.start();
  }
}