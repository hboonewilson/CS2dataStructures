import java.util.Comparator;

public class StudentsByName implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        //Your code here...
        String fullName1 = s1.lastName().concat(s1.firstName());
        String fullName2 = s2.lastName().concat(s2.firstName());
        return fullName1.compareTo(fullName2);

    }
}