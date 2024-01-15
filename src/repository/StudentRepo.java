package repository;

import entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo implements Repository<Student>{
    private static StudentRepo singleton;

    private static final String PATH = "src/db/student_db.txt";
    private final List<Student> students;
    private StudentRepo(List<Student> students) {
        this.students = students;
    }
    public static StudentRepo getInstance() {
        if (singleton == null) {
            singleton = new StudentRepo(loadData());
        }
        return singleton;
    }

    @SuppressWarnings("unchecked")
    private static List<Student> loadData() {
        try(
                InputStream is = new FileInputStream(PATH);
                ObjectInputStream inputStream = new ObjectInputStream(is)
        ) {
            return (List<Student>)inputStream.readObject();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(Student student) {
        students.add(student);
        updateData();
    }

    private void updateData() {
        try(
                OutputStream os = new FileOutputStream(PATH);
                ObjectOutputStream outputStream = new ObjectOutputStream(os)
        ) {
            outputStream.writeObject(students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
