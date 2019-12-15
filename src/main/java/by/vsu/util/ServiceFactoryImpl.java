package by.vsu.util;
import by.vsu.dao.CourseDao;
import by.vsu.dao.RegistredUserDao;
import by.vsu.dao.TeacherDao;
import by.vsu.dao.impl.CourseDaoImpl;
import by.vsu.dao.impl.RegistredUserDaoImpl;
import by.vsu.dao.impl.TeacherDaoImpl;
import by.vsu.service.CourseServise;
import by.vsu.service.RegistredUserService;
import by.vsu.service.TeacherService;
import by.vsu.service.Transaction;
import by.vsu.service.impl.CourseServiseImpl;
import by.vsu.service.impl.RegistredUserServiceImpl;
import by.vsu.service.impl.TransactionImpl;

import by.vsu.service.impl.TeacherServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactoryImpl implements ServiceFactory {
   private Connection connection;

    @Override
    public CourseServise getCourseServise() throws FactoryException {
        CourseServiseImpl courseServise = new CourseServiseImpl();
        courseServise.setCourseDao(getCourseDao());
        courseServise.setTransaction(getTransaction());
        return courseServise;
    }

    @Override
    public CourseDao getCourseDao() throws FactoryException {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        courseDao.setConnection(getConnection());
        return courseDao;
    }

    @Override
    public RegistredUserService getRegistredUserService() throws FactoryException{
        RegistredUserServiceImpl userService = new RegistredUserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setTransaction(getTransaction());
        userService.setUserDao(getRegistredUserDao());
        return userService;
    }

    @Override
    public RegistredUserDao getRegistredUserDao() throws FactoryException {
        RegistredUserDaoImpl registredUserDao = new RegistredUserDaoImpl();
        registredUserDao.setConnection(getConnection());
        return registredUserDao;
    }

    @Override
    public TeacherService getTeacherService() throws FactoryException {
       TeacherServiceImpl teacherService = new TeacherServiceImpl();
       teacherService.setTeacherDao(getTeacherDao());
       teacherService.setTransaction(getTransaction());
       return teacherService;

   }

@Override
    public Transaction getTransaction() throws FactoryException {
       TransactionImpl transaction = new TransactionImpl();
       transaction.setConnection(getConnection());
       return transaction;
}

@Override
    public TeacherDao getTeacherDao() throws FactoryException {
    TeacherDaoImpl teacherDao = new TeacherDaoImpl();
    teacherDao.setConnection(getConnection());
    return teacherDao;
}

@Override
    public Connection getConnection() throws FactoryException {
       if(connection == null){
           try{
               connection=Connector.getConnection();
           }catch (SQLException e){
               throw new FactoryException(e);
           }
       }
       return connection;
}

@Override
    public void close(){
       try{
           connection.close();
           connection=null;
       }catch (Exception e){}
}

}
