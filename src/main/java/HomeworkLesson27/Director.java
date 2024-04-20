package HomeworkLesson27;

public class Director extends FilmStudioEmployee{

    public Director(String name, String position, int experienceYears, int id, AccessLevel accessLevel) {
        super(name, position, experienceYears, id, accessLevel);
    }

    @Override
    public void work(){
        super.work();
        System.out.println("He is leading the film crew");
    }
}
