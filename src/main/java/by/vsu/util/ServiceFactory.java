package by.vsu.util;

import by.vsu.dao.CourseDao;
import by.vsu.dao.RegistredUserDao;
import by.vsu.dao.StudentDao;
import by.vsu.dao.TeacherDao;

import by.vsu.service.*;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {

    TeacherService getTeacherService() throws FactoryException;

    Transaction getTransaction() throws FactoryException;

    TeacherDao getTeacherDao() throws FactoryException;

    Connection getConnection() throws FactoryException;

    StudentService getStudentService() throws FactoryException;

    StudentDao getStudentDao() throws FactoryException;

    RegistredUserService getRegistredUserService() throws FactoryException;

    RegistredUserDao getRegistredUserDao() throws FactoryException;

    CourseServise getCourseServise() throws FactoryException;

    CourseDao getCourseDao() throws FactoryException;

}
