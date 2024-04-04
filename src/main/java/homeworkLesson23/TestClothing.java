package homeworkLesson23;

import java.util.HashSet;

public class TestClothing {

    public static void main(String[] args) {

        ClothingManager clothesCatalog = new ClothingManager();

        clothesCatalog.addClothes(new ClothingItem("Adidas", "45", "White", "Shoes" ));
        clothesCatalog.addClothes(new ClothingItem("Nike", "45", "White", "Shoes" ));
        clothesCatalog.addClothes(new ClothingItem("Nike", "43", "White", "Shoes" ));
        clothesCatalog.addClothes(new ClothingItem("Adidas", "43", "Black", "Shoes" ));
        clothesCatalog.addClothes(new ClothingItem("Wrangler", "36", "Blue", "Trousers" ));
        clothesCatalog.addClothes(new ClothingItem("Lee", "36", "Blue", "Trousers" ));
        clothesCatalog.addClothes(new ClothingItem("Armani", "34", "Black", "Trousers" ));
        clothesCatalog.addClothes(new ClothingItem("Nike", "XL", "Black", "T-shirt" ));
        clothesCatalog.addClothes(new ClothingItem("Nike", "L", "White", "T-shirt" ));
        clothesCatalog.addClothes(new ClothingItem("Adidas", "L", "White", "T-shirt" ));
        clothesCatalog.addClothes(new ClothingItem("Lacoste", "XL", "Black", "Jacket" ));
        clothesCatalog.addClothes(new ClothingItem("Tommy", "XL", "Black", "Jacket" ));
        clothesCatalog.addClothes(new ClothingItem("Diadora", "41", "Blue", "Shoes" ));
        clothesCatalog.addClothes(new ClothingItem("Armani", "52", "White", "Shirt" ));
        clothesCatalog.addClothes(new ClothingItem("Bugatti", "51", "Black", "Shirt" ));


        ClothingManager.printer(ClothingManager.getClothesCatalog());
        System.out.println("Элементы с разным именем но одинаковыми параметрами небыли добавлены");

        System.out.println("\n Фильтруем по категории");
        ClothingManager.printer(ClothingManager.filterByCategory("Shoes"));

        System.out.println("\n Фильтруем по размеру");
        ClothingManager.printer(ClothingManager.filterBySize("XL"));

        System.out.println("\n Фильтруем по бренду");
        ClothingManager.printer(ClothingManager.filterByName("Adidas"));

        System.out.println("\n Фильтруем по цвету");
        ClothingManager.printer(ClothingManager.filterByColor("Black"));

        System.out.println("\n Удаляем элемент из каталога");
        ClothingManager.deleteItem(ClothingManager.getClothesCatalog(),"Bugatti", "51", "Black", "Shirt");

        System.out.println("\nВыводим результат удаления на экран");
        ClothingManager.printer(ClothingManager.getClothesCatalog());
    }
}
