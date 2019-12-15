package by.vsu.service;

import by.vsu.pojo.RegisteredUser;

import java.util.List;

public interface RegistredUserService {
    RegisteredUser findById(Long id) throws ServiceException;

    RegisteredUser findByLoginAndPassword(String login, String password) throws ServiceException;

    List<RegisteredUser> findAll() throws ServiceException;

    void save(RegisteredUser user) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException;



    void delete(Long id) throws ServiceException;
}
