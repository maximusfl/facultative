package by.vsu.pojo;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

    private Long id;
    private Teacher teacher;
    private String courseName;
    private List<Student> students;

    public Course() {
    }

    public Course(Long id, Teacher teacher, String courseName, List<Student> students) {
        this.id = id;
        this.teacher = teacher;
        this.courseName = courseName;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
                "id=" + id +
                ", teacher=" + teacher +
                ", courseName='" + courseName + '\'' +
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
