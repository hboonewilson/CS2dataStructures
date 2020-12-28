import java.util.Comparator;

public class StudentsByID implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        //Your code here...
        Integer s1Int = s1.id();
        Integer s2Int = s2.id();
        return s1Int.compareTo(s2Int);
    }

}