package by.vsu.pojo;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {

    private Long id;
    private String first_name;
    private String last_name;
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String first_name, String last_name, List<Course> courses) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Teacher{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (first_name != null ? !first_name.equals(teacher.first_name) : teacher.first_name != null) return false;
        if (last_name != null ? !last_name.equals(teacher.last_name) : teacher.last_name != null) return false;
        return courses != null ? courses.equals(teacher.courses) : teacher.courses == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }
}
