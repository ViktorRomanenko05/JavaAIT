package homeworkLesson29;

import java.util.ArrayList;
import java.util.HashMap;

public class TestGalery {

    public static void main(String[] args) {

        ArtGallery artGallery = new ArtGallery();

        ArrayList<Artwork> interfaces = new ArrayList<>();

        //Создадим для демонстрации несколько объектов произведений искусства
        Painting painting1 = new Painting(1,"Sophia Martinez", "Silent Reverie", 1967, "Restoration in 2026", ObjectType.PAINTING, TypeOfPaint.OIL, 0.40,0.25);
        Painting painting2 = new Painting(2,"Liam Johnson", "Burst of Light", 2003, "Restoration in 2028", ObjectType.PAINTING, TypeOfPaint.OIL, 0.60,0.30);
        Painting painting3 = new Painting(3,"Olivia Smith", "Winter Echoes", 1935, "Restoration in 2027", ObjectType.PAINTING, TypeOfPaint.OIL, 0.85,0.90);
        Painting painting4 = new Painting(4,"Noah Hernandez", "Fallen Petals", 1987, "Restoration in 2025", ObjectType.PAINTING, TypeOfPaint.OIL, 1.2,0.8);
        Painting painting5 = new Painting(5,"Emma Garcia", "Seaside Dream", 2011, "Restoration in 2028", ObjectType.PAINTING, TypeOfPaint.OIL, 0.4,0.6);
        Sculpture sculpture1 = new Sculpture(6,"Benjamin Lee", "Echo of Time", 1987,"Restoration in 2027", ObjectType.SCULPTURE,"Bronze", 5.1);
        Sculpture sculpture2 = new Sculpture(7,"Mia Wilson", "Skyward Wing", 1979, "Restoration in 2028", ObjectType.SCULPTURE,  "Steel",2.4);
        Sculpture sculpture3 = new Sculpture(8,"Lucas Wong", "Iron Bloom", 2010, "Restoration in 2025", ObjectType.SCULPTURE, "Glass", 8.1);
        Sculpture sculpture4 = new Sculpture(9,"Amelia Brown", "Velvet Chains", 2015, "Restoration in 2029", ObjectType.SCULPTURE, "Plastic", 7.6);
        Sculpture sculpture5 = new Sculpture(10,"Ethan Anderson", "Ocean's Gate", 2001, "Restoration in 2026", ObjectType.SCULPTURE, "Wood", 4.4);

        //Добавим их в лист
        artGallery.addWorkOfArt(painting1);
        artGallery.addWorkOfArt(painting2);
        artGallery.addWorkOfArt(painting3);
        artGallery.addWorkOfArt(painting4);
        artGallery.addWorkOfArt(painting5);
        artGallery.addWorkOfArt(sculpture1);
        artGallery.addWorkOfArt(sculpture2);
        artGallery.addWorkOfArt(sculpture3);
        artGallery.addWorkOfArt(sculpture4);
        artGallery.addWorkOfArt(sculpture5);

        //Создадим несколько интерфейсов
        Artwork artwork1 = painting1;
        Artwork artwork2 = painting2;
        Artwork artwork3 = sculpture4;

        //Также можно создать таким образом:
        Artwork artwork4 = new Painting(11,"Ava Taylor", "Mystic Forest", 1998, "Restoration in 2028", ObjectType.PAINTING, TypeOfPaint.OIL, 0.45,0.30);
        Artwork artwork5 = new Sculpture(12,"Oliver Thompson","Silent Guard",2020, "Restoration i 2026",ObjectType.SCULPTURE,"Steel", 12.7);

        //Продемонстрируем работу методов интерфейсов, реализуемых разными классами

        artwork1.displayInfo();
        artwork5.displayInfo();

        artwork3.getCreator();
        artwork4.getCreator();

        //Интерфейсы одного типа но реализованные разными классами можно хранить в одном хранилище
        interfaces.add(artwork1);
        interfaces.add(artwork2);
        interfaces.add(artwork3);
        interfaces.add(artwork4);
        interfaces.add(artwork5);
        System.out.println("\nВ хранилище interfaces добавлено " + interfaces.size() + " объектов\n");

        //Выведем на экран все объекты
        artGallery.printAllWorks();

        //Отфильтруем по типу и выведем на экран результат
        ArrayList <WorkOfArt> found = artGallery.sortByType(ObjectType.PAINTING);
        artGallery.printer(found);

        //Ищем по имени автора
        found = artGallery.searchByName("Noah Hernandez");
        artGallery.printer(found);

        //Удалим объект и попытаемся удалить еще раз для проверки работы метода
        artGallery.removeWorkOfArt(painting5);
        artGallery.removeWorkOfArt(painting5);
    }
}
