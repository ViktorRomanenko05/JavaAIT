package homeworkLesson32;

public class Greeting {

    public String greeting (String name){
        String greeting;
        if (name != null) {
            greeting = "Hello " + name;
        }
        else {
            greeting = "Hello";
        }
        return greeting;
    }
}
