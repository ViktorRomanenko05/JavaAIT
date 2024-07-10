package backend.program;

public enum AccessLevel {

    DIRECTOR(3, "Admin"),
    MANAGER(2, "Manager"),
    USER (1, "Active user"),
    NONE(0, "Access denied");

    private final int level;

    private final  String description;

    AccessLevel(int level, String description) {
        this.level = level;
        this.description = description;
    }

    //Методы для определения уровня доступа
    public boolean fullAccess(){
        return this.level == DIRECTOR.level;
    }

    public boolean workspaceAccess(){
        return this.level >= MANAGER.level;
    }

    public boolean clientspaceAccess(){
        return this.level >= USER.level;
    }

}
