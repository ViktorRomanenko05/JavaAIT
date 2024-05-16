package homeworkLesson32;

import java.util.ArrayList;

public class StudentsFilter {

    ArrayList<Student> allStudents= new ArrayList<>();

    public ArrayList filterByGrade (ArrayList<Student> students){
        ArrayList<Student> found = new ArrayList<>();
        for(Student student : students){
            if (student != null && student.getGrade()>75){
                found.add(student);
            }
        }
        return found;
    }
}
