package by.vsu.dao.impl;

import by.vsu.dao.CourseDao;
import by.vsu.dao.DaoException;
import by.vsu.pojo.Course;



public class CourseDaoImpl extends EnableConnectionDao implements CourseDao {



    @Override
    public Course read(Long id) throws DaoException {
        return null;
    }

    @Override
    public Long create(Course entity) throws DaoException {
        return null;
    }

    @Override
    public void update(Course entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
