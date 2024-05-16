package homeworkLesson33;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClothingManager {

    List<ClothingItem> clothingItems = new ArrayList<>();

    public List<ClothingItem> getClothingItems() {
        return new ArrayList<>(clothingItems);
    }

    //Метод для добавления одежды
    public void addClothingItem (ClothingItem item){
        if (item == null){
            System.err.println("Error!! Clothing Item is null. It was not added!");
        } else {
            clothingItems.add(item);
            System.out.println("Clothing item " + item.getType() + " " +  item.getName() + " was successfully added");
        }
    }

    //Метод для удаления одежды по имени
    public boolean removeClothingItem(String name) {
        int counter = 0;
        if (name == null) {
            System.err.println("\nError!! Name is null");
            return false;
        } else if (clothingItems.isEmpty()) {
            System.out.println("\nItems list is empty");
            return false;
        } else {
            Iterator<ClothingItem> iterator = clothingItems.iterator();
            while (iterator.hasNext()) {
                ClothingItem item = iterator.next();
                if (item.getName().equalsIgnoreCase(name)) {
                    iterator.remove();
                    counter++;
                    System.out.println("Clothing item " + item.getType() + " " +  item.getName() +" was removed");
                }
            }
        }
        if (counter > 0) {
            System.out.println("\n" + counter + " items were removed");
            return true;
        } else {
            System.out.println("\nItems with name \"" + name + "\" were not found");
            return false;
        }
    }

    //Метод для фильтрации одежды по типу
    public List<ClothingItem> filterByType (String type){
        List <ClothingItem> foundItemsType = new ArrayList<>();
        if (type == null){
            System.err.println("\nError!! Type is null");
            return foundItemsType;
        }
        else if (clothingItems.isEmpty()){
            System.out.println("\nItems list is empty");
            return foundItemsType;
        }
        else {
            Iterator <ClothingItem> iterator = clothingItems.iterator();
            while (iterator.hasNext()){
                ClothingItem item = iterator.next();
                if (item.getType().equalsIgnoreCase(type)){
                    foundItemsType.add(item);
                }
            }
        }
        System.out.println(!foundItemsType.isEmpty() ? foundItemsType.size() + " items was found" : "Items of type " + type + " was not found");
        return foundItemsType;
    }

    //Метод для фильтрации одежды по размеру
    public List<ClothingItem> filterBySize (String size){
        List <ClothingItem> foundItemsSize = new ArrayList<>();
        if (size == null){
            System.err.println("\nError!! Size is null");
            return foundItemsSize;
        }
        else if (clothingItems.isEmpty()){
            System.out.println("\nItems list is empty");
            return foundItemsSize;
        }
        else {
            Iterator <ClothingItem> iterator = clothingItems.iterator();
            while (iterator.hasNext()){
                ClothingItem item = iterator.next();
                if (item.getSize().equalsIgnoreCase(size)){
                    foundItemsSize.add(item);
                }
            }
        }
        System.out.println(!foundItemsSize.isEmpty() ? foundItemsSize.size() + " items was found" : "Items with size " + size + " was not found");
        return foundItemsSize;
    }

    //Метод для поиска самого дешёвого элемента одежды
    public ClothingItem findCheapestItem (){
        if (clothingItems.isEmpty()){
            System.out.println("\nItems list is empty");
            return null;
        }
        else {
            ClothingItem cheapestItem = clothingItems.getFirst();
            for (ClothingItem item : clothingItems){
                if(item.getPrice() < cheapestItem.getPrice()){
                    cheapestItem = item;
                }
            }
            return cheapestItem;
        }
    }


}
