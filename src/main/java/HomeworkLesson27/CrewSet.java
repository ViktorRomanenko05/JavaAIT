package HomeworkLesson27;

import java.util.HashMap;

public class CrewSet {

    static HashMap <Integer, FilmStudioEmployee> team = new HashMap<>();

    //Метод для добавления профилей сотрудников в хранилище
    public void addTeamMember(FilmStudioEmployee employee) {
        if (employee == null) {
            System.out.println("Object employee is null");
        } else {
            FilmStudioEmployee previousEmployee = team.put(employee.getId(), employee);
            if (previousEmployee == null) {
                System.out.println("Team member profile " + employee.getName() + " successfully added");
            } else {
                System.out.println("Team member profile " + employee.getName() + " was updated");
            }
        }
    }

    //Метод для иммитации рабочего процесса, требуемый по заданию
    public void simulateDayOfWork(HashMap <Integer, FilmStudioEmployee> team){
        for (FilmStudioEmployee employee : team.values()){
            employee.work();
        }
    }
}
