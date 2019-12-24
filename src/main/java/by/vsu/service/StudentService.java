package by.vsu.service;

import by.vsu.dao.DaoException;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;

import java.util.List;

public interface StudentService {

    Student findById(Long id) throws ServiceException;

    List<Student> findAll() throws ServiceException;

    List<Student> findByCourseId(Long id) throws ServiceException;

    void save(Student student) throws ServiceException;

    void delete(Long id) throws  ServiceException;

}
