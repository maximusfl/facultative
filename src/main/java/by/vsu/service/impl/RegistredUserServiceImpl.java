package by.vsu.service.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.RegistredUserDao;
import by.vsu.pojo.RegisteredUser;
import by.vsu.service.*;

import java.util.List;

public class RegistredUserServiceImpl extends EnableTransactionService implements RegistredUserService {
    private RegistredUserDao userDao;
    private String defaultPassword;

    public void setUserDao(RegistredUserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public RegisteredUser findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RegisteredUser findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RegisteredUser> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(RegisteredUser user) throws ServiceException {
        try {
            getTransaction().start();
            if(user.getId() != null) {
                RegisteredUser storedUser = userDao.read(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                user.setPassword(defaultPassword);
                if(userDao.readByLogin(user.getLogin()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        } catch (UserNotExistsException e) {
            e.printStackTrace();
        } catch (UserLoginNotUniqueException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            RegisteredUser user = userDao.read(userId);
            if(user != null) {
                if(user.getPassword().equals(oldPassword)) {
                    if(newPassword == null) {
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        } catch (UserNotExistsException e) {
            e.printStackTrace();
        } catch (UserPasswordIncorrectException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
