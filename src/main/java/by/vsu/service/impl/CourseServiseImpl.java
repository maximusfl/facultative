package by.vsu.service.impl;

import by.vsu.dao.CourseDao;
import by.vsu.dao.DaoException;
import by.vsu.pojo.Course;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;

import java.util.List;


public class CourseServiseImpl extends EnableTransactionService implements CourseServise {
    private CourseDao courseDao;

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public void save(Course course) throws ServiceException {

    }

    @Override
    public void delete(Long id) throws ServiceException {

    }

    @Override
    public List<Course> findAll() throws ServiceException {
        try {
            return courseDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
