package by.vsu.pojo;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

    private int id;
    private Teacher teacher;
    private List<Student> students;

    public Course() {
    }

    public Course(Teacher teacher, List<Student> students) {
        this.teacher = teacher;
        this.students = students;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

        public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "teacher=" + teacher +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (teacher != null ? !teacher.equals(course.teacher) : course.teacher != null) return false;
        return students != null ? students.equals(course.students) : course.students == null;
    }

    @Override
    public int hashCode() {
        int result = teacher != null ? teacher.hashCode() : 0;
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }
}
