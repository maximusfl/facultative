package by.vsu.util;
import by.vsu.dao.TeacherDao;
import by.vsu.dao.impl.TeacherDaoImpl;
import by.vsu.service.TeacherService;
import by.vsu.service.Transaction;
import by.vsu.service.impl.TransactionImpl;

import by.vsu.service.impl.TeacherServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactoryImpl implements ServiseFactory {
   private Connection connection;

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
