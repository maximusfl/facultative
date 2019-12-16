package by.vsu.dao.impl;

import by.vsu.dao.CourseDao;
import by.vsu.dao.DaoException;
import by.vsu.pojo.Course;
import by.vsu.pojo.Teacher;
import com.mysql.jdbc.log.Log;

import java.sql.PreparedStatement;
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
        logger.info("try to find course by id...");
        String sql = "SELECT `teacher_id`, `course_name` FROM `courses` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Course course = null;
            if(resultSet.next()) {
                course = new Course();
                course.setId(id);
                Long teacherId = Long.parseLong(resultSet.getString("teacher_id"));
                Teacher teacher = new Teacher();
                teacher.setId(teacherId);
                course.setTeacher(teacher);
                course.setCourseName(resultSet.getString("course_name"));
            }
            return course;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Course course) throws DaoException {
        logger.info("called create method CourseDao");
        String sql = "INSERT INTO `courses` (`teacher_id`, `course_name`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, course.getTeacher().getId());

            statement.setString(2, course.getCourseName());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
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
