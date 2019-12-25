package by.vsu.service.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.RegistredUserDao;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
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
            getTransaction().start();
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RegisteredUser findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RegisteredUser> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(RegisteredUser user, Long id) throws ServiceException {
        try {
            getTransaction().start();
            if (user.getId() != null) {
                RegisteredUser storedUser = userDao.read(user.getId());
                if (storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if (storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                userDao.save(user, id);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
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
            if (user != null) {
                if (user.getPassword().equals(oldPassword)) {
                    if (newPassword == null) {
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
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        } catch (UserNotExistsException e) {
            e.printStackTrace();
        } catch (UserPasswordIncorrectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RegisteredUser findByTeacherId(Long id) throws ServiceException {
        try {
            getTransaction().start();
            RegisteredUser user = userDao.readByTeacherId(id);

            if (user != null) {
                getTransaction().commit();
            }
            return user;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }


    @Override
    public void delete(Long id) throws ServiceException {
        try {
            getTransaction().start();
            userDao.delete(id);
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public RegisteredUser findByLogin(String login) throws ServiceException {
        try {
            getTransaction().start();
            RegisteredUser user = userDao.readByLogin(login);

            if (user != null) {
                getTransaction().commit();

            }
            return user;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void deleteByTeacherId(Long teacherId) throws ServiceException {
        try {
            getTransaction().start();
            userDao.deleteByTeacherId(teacherId);
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public List<RegisteredUser> findAllteacherAccounts() throws ServiceException {
        try {
            getTransaction().start();
            List<RegisteredUser> teacherAccounts = userDao.findAllteacherAccounts();

            if (teacherAccounts != null) {
                getTransaction().commit();
            }
            return teacherAccounts;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public List<Teacher> findTeachersWithAccounts() throws ServiceException {
        try {
            getTransaction().start();
            List<Teacher> teacherAccounts = userDao.findTeachersWithAccounts();
            if (teacherAccounts != null) {
                getTransaction().commit();
            }
            return teacherAccounts;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public List<Student> findStudentsWithAccounts() throws ServiceException {
        try {
            getTransaction().start();
            List<Student> studentAccounts = userDao.findStudentsWithAccounts();

            if (studentAccounts != null) {
                getTransaction().commit();
            }
            return studentAccounts;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public RegisteredUser findByStudentId(Long student_id) throws ServiceException {
        try {
            getTransaction().start();
            RegisteredUser user = userDao.readByStudentId(student_id);

            if (user != null) {
                getTransaction().commit();
            }
            return user;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void saveStudentAccount(RegisteredUser registeredUser, Long student_id) throws ServiceException {
        try {
            getTransaction().start();
            userDao.saveStudentAccount(registeredUser, student_id);
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void deleteByStudentId(Long studentId) throws ServiceException {
        try {
            getTransaction().start();
            userDao.deleteByStudentId(studentId);
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public Long getStudentIdByRegUserId(Long id) throws ServiceException {
        try {
            getTransaction().start();
            Long student_id = userDao.findStudentIdByUserId(id);
            if (student_id != null) {
                getTransaction().commit();
            }
            return student_id;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public Long getTeacherIdByregUserId(Long id) throws ServiceException {
        try {
            getTransaction().start();
            Long teacher_id = userDao.findTeacherIdByUserId(id);
            if (teacher_id != null) {
                getTransaction().commit();
            }
            return teacher_id;
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }
}
