package by.vsu.service;

import by.vsu.pojo.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findById(Long id) throws ServiceException;

    List<Teacher> findAll() throws ServiceException;

    void save(Teacher teacher) throws ServiceException;

    void delete(Long id) throws  ServiceException;

    Teacher findByCourseId(Long id) throws ServiceException;
}
