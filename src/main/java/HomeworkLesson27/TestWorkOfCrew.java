package HomeworkLesson27;

public class TestWorkOfCrew {
    public static void main(String[] args) {

        CrewSet movieSet = new CrewSet();
        WorkSpace workSpace = new WorkSpace();

        //Создадим объекты всех членов съемочной группы задействованных в рабочем процессе
        Producer producer = new Producer("Alex", "producer", 10, 1011, AccessLevel.PRODUCER);
        Director director = new Director("Peter", "director", 12,1000, AccessLevel.DIRECTOR);
        Actor actor = new Actor("James", "actor", 8, 1001, AccessLevel.ACTOR);
        CameraOperator operator = new CameraOperator("Alice", "camera operator", 20, 1010, AccessLevel.TECHNICAL_STAFF);
        SoundEngineer soundEngineer = new SoundEngineer("Max", "sound engineer", 5, 1111, AccessLevel.TECHNICAL_STAFF);
        Editor editor = new Editor("Eric", "editor", 7, 1101, AccessLevel.TECHNICAL_STAFF);

        //Добавим созданные объекты в хранилище
        movieSet.addTeamMember(producer);
        movieSet.addTeamMember(director);
        movieSet.addTeamMember(actor);
        movieSet.addTeamMember(operator);
        movieSet.addTeamMember(soundEngineer);
        movieSet.addTeamMember(editor);

        //Для проверки реакции метода добавим 2 объекта повторно
        movieSet.addTeamMember(director);
        movieSet.addTeamMember(soundEngineer);


        //Выводим на экран список всех членов съемочной группы из хранилища.
        System.out.println("\nСписок всех членов съемочной групы");
        line();
        System.out.printf("%-3s %-6s %-10s %-17s %s%n","№", "ID", "Name", "Position", "Experience");
        line();
        int counter = 0;
        for (FilmStudioEmployee employee : CrewSet.team.values()){
            counter++;
            System.out.printf("%-3s %-6s %-10s %-17s %s%n", counter, employee.getId(),employee.getName(),employee.getPosition(),"        "+employee.getExperienceYears());

        }
        line();

        //Запустим переопределенные методы work(), как указано в задании
        movieSet.simulateDayOfWork(CrewSet.team);

        //Запустим различные методы WorkSpace и передадим разных пользователей для проверки контроля доступа
        workSpace.projectManger(producer);
        workSpace.filmCrewManagement(operator);
        workSpace.videoEditor(operator);
        workSpace.roles(actor);
        workSpace.roles(operator);
        workSpace.roles(producer);
    }

    public static void line(){
        System.out.println("--------------------------------------------------");
    }
}
