package by.vsu.dao.impl;

import by.vsu.dao.DaoException;
import by.vsu.pojo.Teacher;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.service.impl.TeacherServiceImpl;
import by.vsu.util.Connector;
import by.vsu.util.FactoryException;
import by.vsu.util.ServiceFactoryImpl;
import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

import static by.vsu.TestDataFactory.*;
import static org.junit.Assert.*;

@RunWith(ConcurrentTestRunner.class)
public class TeacherDaoImplTest {

    @BeforeClass

    public static void init() {
        try {
            Connector.init(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/facultative?useUnicode=true&useSSL=false&characterEncoding=UTF-8",
                    "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFirstConnection() {
        DaoException daoException = null;
        SQLException sqlException = null;

        TeacherDaoImpl teacherDao = getTestTeacherDao();
        Teacher teacher = getTestTeacher();
        try {
            teacherDao.create(teacher);
            teacherDao.getConnection().close();
        } catch (DaoException e) {
            daoException = e;
            e.printStackTrace();
        } catch (SQLException e) {
            sqlException = e;
            e.printStackTrace();
        }
        assertNull(daoException);
        assertNull(sqlException);

    }

    @Test
    @ThreadCount(70)
        public void testTeacherServiceRead() throws FactoryException {
        ServiceFactoryImpl serviceFactory = new ServiceFactoryImpl();
        TeacherService teacherService = serviceFactory.getTeacherService();
        try {
            Teacher teacher = teacherService.findById(1L);
            System.out.println(teacher);
            System.out.println(Thread.currentThread().getName());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    @Test
    @ThreadCount(70)
    public void testTeacherServiceRead2() throws FactoryException {
        ServiceFactoryImpl serviceFactory = new ServiceFactoryImpl();
        TeacherService teacherService = serviceFactory.getTeacherService();
        try {
            Teacher teacher = teacherService.findById(2L);
            System.out.println(teacher);
            System.out.println(Thread.currentThread().getName());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}