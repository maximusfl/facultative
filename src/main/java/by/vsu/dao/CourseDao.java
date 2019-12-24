package by.vsu.dao;

import by.vsu.pojo.Course;
import by.vsu.pojo.Student;

import java.util.List;

public interface CourseDao extends Dao<Course>{
    List<Course> readAll() throws DaoException;

    void deleteStudentByCourseId(Long courseId, Long studentId) throws DaoException;

    void saveStudentToCourse(Long courseId, Long studentId) throws DaoException;

    void updateTeacherOnCourse(Long courseId, Long teacherId) throws DaoException;

    List<Course> readAllWithTeachers() throws DaoException;

    List<Course> readAllWithStudent(Long studentId) throws DaoException;

    Long getRaitingByStudentIdAndCourseId(Long student_id, Long courseId) throws DaoException;

    String getResumeByStudentIdAndCourseId(Long student_id, Long courseId) throws DaoException;

    List<Course> readAllTeachersCourses(Long teacher_id) throws DaoException;

    List<Student> findStudentsByCourseId(Long courseId) throws DaoException;

    void AddRaiting(Long courseId, Long studentId, Long raiting, String resume) throws DaoException;
}
