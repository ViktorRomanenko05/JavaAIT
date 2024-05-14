package homeworkLesson32;

public class StringCase {

    public String stringToUpperCase (String word){
        if (word != null){
        String caseUp;
        caseUp = word.toUpperCase();
        return caseUp;
        }

        else {
            System.err.println("ERROR!! Input is null");
            return null;}

    }
}
