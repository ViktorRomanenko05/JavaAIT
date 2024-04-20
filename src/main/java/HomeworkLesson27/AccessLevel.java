package HomeworkLesson27;

public enum AccessLevel {

    PRODUCER(3, "Full access"),
    DIRECTOR(2, "Acces to managing the filming process, technical & artistic sections"),
    TECHNICAL_STAFF(1, "Access to technical functionality"),
    ACTOR(0 , "Artistic sections");

    int level;
    String description;

    AccessLevel(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public boolean fullAccess(){
        return this.level == PRODUCER.level;
    }

    public boolean accessToTheFilmingProcess(){
        return this.level >= DIRECTOR.level;
    }

    public boolean accessToTechnicalSpace(){
        return this.level >= TECHNICAL_STAFF.level;
    }

    public boolean accessToArtistSpace(){
        return this.level >= ACTOR.level && this.level != TECHNICAL_STAFF.level;
    }
}
