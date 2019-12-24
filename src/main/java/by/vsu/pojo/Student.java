package by.vsu.pojo;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    private  Long id;
    private String first_name;
    private String last_name;
    private List<Course> courses;

    public Student() {
    }

    public Student(String first_name, String last_name, List<Course> courses) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.courses = courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (first_name != null ? !first_name.equals(student.first_name) : student.first_name != null) return false;
        if (last_name != null ? !last_name.equals(student.last_name) : student.last_name != null) return false;
        return courses != null ? courses.equals(student.courses) : student.courses == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }
}
