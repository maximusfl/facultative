package by.vsu.service;

import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;

import java.util.List;

public interface RegistredUserService {
    RegisteredUser findById(Long id) throws ServiceException;

    RegisteredUser findByLoginAndPassword(String login, String password) throws ServiceException;

    List<RegisteredUser> findAll() throws ServiceException;

    void save(RegisteredUser user, Long id) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException;

    public RegisteredUser findByTeacherId(Long ig) throws ServiceException;

    void delete(Long id) throws ServiceException;

    RegisteredUser findByLogin(String login) throws ServiceException;

    void deleteByTeacherId(Long teacherId) throws ServiceException;

    List<RegisteredUser> findAllteacherAccounts() throws ServiceException;

    List<Teacher> findTeachersWithAccounts() throws ServiceException;

    List<Student> findStudentsWithAccounts() throws ServiceException;

    RegisteredUser findByStudentId(Long student_id) throws ServiceException;

    void saveStudentAccount(RegisteredUser registeredUser, Long student_id) throws ServiceException;

    void deleteByStudentId(Long studentId) throws ServiceException;


    Long getStudentIdByRegUserId(Long id) throws ServiceException;

    Long getTeacherIdByregUserId(Long id) throws ServiceException;
}
