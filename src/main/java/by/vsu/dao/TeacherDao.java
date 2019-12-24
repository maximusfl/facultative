package by.vsu.dao;


import by.vsu.pojo.Teacher;

import java.util.List;

public interface TeacherDao extends Dao<Teacher> {
    List<Teacher> readAll() throws DaoException;

    Teacher readByCourseId(Long id) throws DaoException;
}
