import entity.Student;
import repository.StudentRepo;

public class Main {
    public static void main(String[] args) {

        StudentRepo studentRepo = StudentRepo.getInstance();
        for (Student student : studentRepo.findAll()) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }


    }
}