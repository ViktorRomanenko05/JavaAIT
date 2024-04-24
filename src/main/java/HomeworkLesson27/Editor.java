package HomeworkLesson27;

public class Editor extends FilmStudioEmployee {

    public Editor(String name, String position, int experienceYears, int id, AccessLevel accessLevel) {
        super(name, position, experienceYears, id, accessLevel);
    }

    @Override
    public void work(){
        super.work();
        System.out.println("Edits video");
    }
}
