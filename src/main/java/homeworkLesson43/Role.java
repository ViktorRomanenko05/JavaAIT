package homeworkLesson43;

public enum Role {

    NONE(0),
    USER(1),
    ADMIN(2);

    private final int level;

    Role(int level) {
        this.level = level;
    }

    public boolean isUser(){
        return this.level == USER.level;
    }

    public boolean isAdmin(){
        return this.level == ADMIN.level;
    }

    //Приведение значения из String в Role
    public static Role fromStringRole (String role){
        if(role.trim().equalsIgnoreCase("user")){
            return Role.USER;
        }
        else if(role.equals("admin")){
            return Role.ADMIN;
        }
        else {return Role.NONE;}
    }

    //Приведение значения из Role в String
    public static String toStringRole (Role role){
        if(role.equals(Role.USER)){
            return "user";
        }
        else if(role.equals(Role.ADMIN)){
            return "admin";
        }
        else return null;
    }
}
