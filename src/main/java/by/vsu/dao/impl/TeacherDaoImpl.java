package by.vsu.dao.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.TeacherDao;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;
import by.vsu.pojo.Teacher;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class TeacherDaoImpl extends EnableConnectionDao implements TeacherDao {
    private static Logger logger = Logger.getLogger("TeacherDaoImpl");

    @Override
    public List<Teacher> readAll() throws DaoException {
        logger.info("called readAll");
        String sql = "SELECT `id`, `first_name`, `last_name`  FROM `teachers`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Teacher> teachers = new ArrayList<>();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(Long.parseLong(resultSet.getString("id")));
                teacher.setFirst_name(resultSet.getString("first_name"));
                teacher.setLast_name(resultSet.getString("last_name"));
                teachers.add(teacher);
            }
            return teachers;
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
    public Teacher readByCourseId(Long id) throws DaoException {
        String sql = "Select  teachers.first_name, teachers.last_name, teachers.id\n" +
                "From teachers Inner Join\n" +
                "  courses On courses.teacher_id = teachers.id\n" +
                "  where courses.id=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Teacher teacher = null;
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setFirst_name(resultSet.getString("first_name"));
                teacher.setLast_name(resultSet.getString("last_name"));
                teacher.setId(Long.parseLong(resultSet.getString("id")));
            }
            return teacher;
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
    public Teacher read(Long id) throws DaoException {
        String sql = "SELECT `first_name`, `last_name` FROM `teachers` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Teacher teacher = null;
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setId(id);
                teacher.setFirst_name(resultSet.getString("first_name"));
                teacher.setLast_name(resultSet.getString("last_name"));
            }
            return teacher;
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
    public Long create(Teacher teacher) throws DaoException {
        String sql = "INSERT INTO `teachers` (`first_name`, `last_name`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacher.getFirst_name());
            statement.setString(2, teacher.getLast_name());
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
    public void update(Teacher entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `teachers` WHERE `id` = ?";
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
