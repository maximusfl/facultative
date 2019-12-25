package by.vsu.dao.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.StudentDao;
import by.vsu.pojo.Course;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentDaoImpl extends EnableConnectionDao implements StudentDao {
    private static Logger logger = Logger.getLogger("StudentDaoImpl");

    @Override
    public List<Student> findByCourseId(Long id) {
        List<Student> loadedStudents = new ArrayList<>();
        logger.info("try to find list students...");
        String sql = "Select id, first_name,last_name\n" +
                "        From students Right Join\n" +
                "        courses_students On courses_students.student_id = students.id\n" +
                "        where courses_students.course_id =?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                logger.info("student_id: " + student.getId());
                student.setFirst_name(resultSet.getString("first_name"));
                student.setLast_name(resultSet.getString("last_name"));
                loadedStudents.add(student);
                logger.info("size: " + loadedStudents.size());
            }
        } catch (SQLException e) {
            try {
                throw new DaoException(e);
            } catch (DaoException ex) {
                ex.printStackTrace();
            }
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
        return loadedStudents;
    }

    @Override
    public List<Student> findAll() throws DaoException {
        logger.info("called findAll");
        String sql = "SELECT `id`, `first_name`, `last_name`  FROM `students`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(Long.parseLong(resultSet.getString("id")));
                student.setFirst_name(resultSet.getString("first_name"));
                student.setLast_name(resultSet.getString("last_name"));
                students.add(student);
            }
            return students;
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


    @Override
    public Student read(Long id) throws DaoException {
        return null;
    }

    @Override
    public Long create(Student student) throws DaoException {
        String sql = "INSERT INTO `students` (`first_name`, `last_name`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirst_name());
            statement.setString(2, student.getLast_name());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
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

    @Override
    public void update(Student student) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `students` WHERE `id` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}

