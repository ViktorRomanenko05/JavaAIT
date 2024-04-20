package HomeworkLesson27;

public class SoundEngineer extends FilmStudioEmployee {

    public SoundEngineer(String name, String position, int experienceYears, int id, AccessLevel accessLevel) {
        super(name, position, experienceYears, id, accessLevel);
    }

    @Override
    public void work(){
        super.work();
        System.out.println("Works with sound");
    }
}
