package by.vsu.service;

import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Teacher;

import java.util.List;

public interface CourseServise {
    Course findById(Long id) throws ServiceException;



    void save(Course course) throws ServiceException;

    void delete(Long id) throws  ServiceException;

    List<Course> findAll() throws ServiceException;
}
