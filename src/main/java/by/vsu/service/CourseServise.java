package by.vsu.service;

import by.vsu.pojo.Course;
import by.vsu.pojo.Student;


import java.util.List;

public interface CourseServise {
    Course findById(Long id) throws ServiceException;

    List<Student> findStudentsByCourseId(Long id) throws ServiceException;

    void save(Course course) throws ServiceException;

    void delete(Long id) throws  ServiceException;

    List<Course> findAll() throws ServiceException;

    void deleteStudentByCourseId(Long courseId, Long studentId) throws ServiceException;

    void addStudentToCourse(Long courseId, Long studentId) throws ServiceException;

    void changeteacherOnCourse(Long courseId, Long teacherId) throws ServiceException;

    List<Course> findAllWithTeachers() throws ServiceException;

    List<Course> findAllWithStudent(Long student_id) throws ServiceException;

    Long getRaitingByStudentIdAndCourseId(Long student_id, Long courseId) throws ServiceException;

    String getResumeByStudentIdAndCourseId(Long student_id, Long courseId) throws ServiceException;

    List<Course> findAllTeachersCourses(Long teacher_id) throws ServiceException;

    void addRaiting(Long courseId, Long studentId, Long raiting, String resume) throws ServiceException;
}
