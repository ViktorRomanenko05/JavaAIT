package HomeworkLesson27;

public class Producer extends FilmStudioEmployee {

    public Producer(String name, String position, int experienceYears, int id, AccessLevel accessLevel) {
        super(name, position, experienceYears, id, accessLevel);
    }

    @Override
    public void work(){
        super.work();
        System.out.println("Manages the project");
    }
}
