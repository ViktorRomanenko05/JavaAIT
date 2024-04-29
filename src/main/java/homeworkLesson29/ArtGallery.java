package homeworkLesson29;

import java.util.ArrayList;
import java.util.HashMap;

public class ArtGallery {
    private HashMap <Integer, WorkOfArt> worksOfArt = new HashMap<>();

    //Метод для добавления объектов
    public void addWorkOfArt(WorkOfArt workOfArt) {
        if (workOfArt == null) {
            System.out.println("Object user is null");
        } else {
            WorkOfArt previousWork = worksOfArt.put(workOfArt.getArtworkId(), workOfArt);
            if (previousWork == null) {
                System.out.println("Work of art " + workOfArt.getObjectType().description + workOfArt.getName() + " successfully added");
            } else {
                System.out.println("Work of art " + workOfArt.getObjectType().description + workOfArt.getName() + " was updated");
            }
        }
    }
    //Метод для удаления объектов
    public void removeWorkOfArt(WorkOfArt work) {
        if (work == null) {
            System.out.println("Object user is null");
        } else {
            WorkOfArt removedWork = worksOfArt.remove(work.getArtworkId());
            if (removedWork != null) {
                System.out.println("Work of art " + work.getName() + " was removed");
            } else {
                System.out.println("Work of art " + work.getName() + " not found");
            }
        }
    }
    //Метод для сортировки по типу (картина / скульптура)
    public ArrayList<WorkOfArt> sortByType (ObjectType type){

        int counter = 0;
        ArrayList <WorkOfArt> filtered = new ArrayList<>();

        for (WorkOfArt work : worksOfArt.values()){
            if (work.getObjectType().equals(type)){
                counter++;
                filtered.add(work);
            }
        }
        if (!filtered.isEmpty()) {
            System.out.println(counter + " objects was found");
            return filtered;
        }
        else {
            System.out.println("Objects was not found");
            return null;
        }
    }
    //Метод для поиска по имени автора
    public ArrayList <WorkOfArt> searchByName (String name){
        ArrayList<WorkOfArt> found = new ArrayList<>();
        int counter =0;
        for (WorkOfArt work : worksOfArt.values()){
            if (work.getAuthor().equalsIgnoreCase(name)){
                found.add(work);
                counter++;
            }
        }
        if (!found.isEmpty()){
            System.out.println(counter + " works of art was found");
            return found;
        }
        else {
            System.out.println("works of art was not found");
            return  null;
        }
    }

    public void printAllWorks() {
        line();
        System.out.printf("%-3s %-10s %-20s %-6s %-20s %s%n", "№", "Type", "Name", "Year", "Artist", "Description");
        line();
        int counterNum = 0;
        for (WorkOfArt work : worksOfArt.values()) {
            counterNum++;
            System.out.printf("%-3s %-10s %-20s %-6s %-20s %s%n", counterNum, work.getObjectType().description, work.getName(), work.getYearOfCreation(), work.getAuthor(), work.getDescription());
        }
        line();
    }

    public void printer(ArrayList <WorkOfArt> list) {
        line();
        System.out.printf("%-3s %-10s %-20s %-6s %-20s %s%n", "№", "Type", "Name", "Year", "Artist", "Description");
        line();
        int counterNum = 0;
        for (WorkOfArt work : list) {
            counterNum++;
            System.out.printf("%-3s %-10s %-20s %-6s %-20s %s%n", counterNum, work.getObjectType().description, work.getName(), work.getYearOfCreation(), work.getAuthor(), work.getDescription());
        }
        line();
    }

    public void line() {System.out.println("-----------------------------------------------------------------------------------");}
}
