package by.vsu.dao.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.RegistredUserDao;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RegistredUserDaoImpl extends EnableConnectionDao implements RegistredUserDao {
    private static Logger logger = Logger.getLogger("RegistredUserDaoImpl");
    @Override
    public RegisteredUser read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`, `role` FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            RegisteredUser user = null;
            if(resultSet.next()) {
                user = new RegisteredUser();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public RegisteredUser readByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `password`, `role` FROM `user` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            RegisteredUser user = null;
            if(resultSet.next()) {
                user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public RegisteredUser readByLoginAndPassword(String login, String password) throws DaoException {
        logger.info("called  readByLoginAndPassword");

        String sql = "SELECT `id`, `role` FROM `registered_users` WHERE `login` = ? AND `password` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            logger.info("resultSet: " +resultSet.toString());

            RegisteredUser user = null;
            if(resultSet.next()) {

                user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);

                user.setRole(Role.valueOf(resultSet.getString("role")));
                logger.info("role: "+resultSet.getString("role"));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<RegisteredUser> readAll() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role` FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<RegisteredUser> users = new ArrayList<>();
            while(resultSet.next()) {
                RegisteredUser user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }




    @Override
    public Long create(RegisteredUser user) throws DaoException {
        String sql = "INSERT INTO `user` (`login`, `password`, `role`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
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
    public void update(RegisteredUser user) throws DaoException {
        String sql = "UPDATE `user` SET `login` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }
}
