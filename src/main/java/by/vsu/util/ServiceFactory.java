package by.vsu.util;

import by.vsu.dao.TeacherDao;

import by.vsu.service.TeacherService;
import by.vsu.service.Transaction;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {

    TeacherService getTeacherService() throws FactoryException;

    Transaction getTransaction() throws FactoryException;

    TeacherDao getTeacherDao() throws FactoryException;

    Connection getConnection() throws FactoryException;

}
