package HomeworkLesson27;

public class Actor extends FilmStudioEmployee{

    public Actor(String name, String position, int experienceYears, int id, AccessLevel accessLevel) {
        super(name, position, experienceYears, id, accessLevel);
    }

    @Override
    public void work(){
        super.work();
        System.out.println("Plays a role");
    }
}
