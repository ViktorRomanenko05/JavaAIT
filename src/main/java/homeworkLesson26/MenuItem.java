package homeworkLesson26;

public class MenuItem {
    String itemName;
    MenuItemType itemType;

    public MenuItem(String dishName, MenuItemType itemType) {
        this.itemName = dishName;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public MenuItemType getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemName='" + itemName + '\'' +
                ", itemType=" + itemType +
                '}';
    }
}