package by.vsu.dao;

import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;

import java.util.List;

public interface RegistredUserDao extends Dao<RegisteredUser> {
    List<RegisteredUser> readAll() throws DaoException;

    RegisteredUser readByLogin(String login) throws DaoException;

    void save(RegisteredUser registeredUser, Long id) throws DaoException;

    RegisteredUser readByLoginAndPassword(String login, String password) throws DaoException;


    RegisteredUser readByTeacherId(Long id) throws DaoException;

    void deleteByTeacherId(Long teacherId) throws DaoException;

    List<RegisteredUser> findAllteacherAccounts() throws DaoException;

    List<Teacher> findTeachersWithAccounts() throws DaoException;

    List<Student> findStudentsWithAccounts() throws DaoException;

    RegisteredUser readByStudentId(Long student_id) throws DaoException;

    void saveStudentAccount(RegisteredUser registeredUser, Long student_id) throws DaoException;

    void deleteByStudentId(Long studentId) throws DaoException;

    Long findStudentIdByUserId(Long id) throws DaoException;

    Long findTeacherIdByUserId(Long id) throws DaoException;
}
