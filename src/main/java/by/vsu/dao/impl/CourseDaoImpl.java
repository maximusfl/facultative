package by.vsu.dao.impl;

import by.vsu.dao.CourseDao;
import by.vsu.dao.DaoException;
import by.vsu.pojo.Course;
import com.mysql.jdbc.log.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class CourseDaoImpl extends EnableConnectionDao implements CourseDao {
    private static Logger logger = Logger.getLogger("CourseDaoImpl");


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

    @Override
    public List<Course> readAll() throws DaoException {
        logger.info("try to find list courses...");
        String sql = "SELECT `id`, `course_name` FROM `courses`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                logger.info("course_id: " +course.getId());
                course.setCourseName(resultSet.getString("course_name"));
                logger.info("course_name: " +course.getCourseName());

                courses.add(course);
                logger.info("size: "+ courses.size());
            }
            return courses;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

}
