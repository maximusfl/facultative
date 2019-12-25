package by.vsu.dao;

import by.vsu.pojo.Student;

import java.util.List;

public interface StudentDao extends Dao<Student> {

    List<Student> findByCourseId(Long id) throws DaoException;

    List<Student> findAll() throws DaoException;
}
