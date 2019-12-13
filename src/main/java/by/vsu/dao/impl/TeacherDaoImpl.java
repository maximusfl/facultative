package by.vsu.dao.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.TeacherDao;
import by.vsu.pojo.Teacher;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TeacherDaoImpl extends EnableConnectionDao implements TeacherDao {


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
    public void update(Teacher entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
