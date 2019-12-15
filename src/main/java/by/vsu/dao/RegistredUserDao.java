package by.vsu.dao;

import by.vsu.pojo.RegisteredUser;

import java.util.List;

public interface RegistredUserDao extends Dao<RegisteredUser> {
    List<RegisteredUser> readAll() throws DaoException;

    RegisteredUser readByLogin(String login) throws DaoException;

    RegisteredUser readByLoginAndPassword(String login, String password) throws DaoException;

}
