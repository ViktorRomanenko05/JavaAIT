package homeworkLesson26;

public class Menu {
    public static void main(String[] args) {
        MenuItem coffee = new MenuItem("Coffee", MenuItemType.DRINK);
        MenuItem pizza = new MenuItem("Pizza", MenuItemType.DISH);

        System.out.println(coffee.getItemName() + " is " + coffee.itemType);
        System.out.println(pizza.getItemName() + " is " + pizza.itemType);
    }
}
