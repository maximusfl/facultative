package by.vsu;

import by.vsu.dao.impl.TeacherDaoImpl;
import by.vsu.pojo.Teacher;
import by.vsu.service.impl.TeacherServiceImpl;
import by.vsu.util.Connector;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDataFactory {
    static Connection connection;

    public static Teacher getTestTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirst_name("Maxim");
        teacher.setLast_name("Kotov");
        return teacher;
    }

    public static TeacherDaoImpl getTestTeacherDao() {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        teacherDao.setConnection(getTestConnection());
        return teacherDao;
    }

    public static   Connection getTestConnection() {
        if (connection == null) {
            try {
                connection = Connector.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static TeacherServiceImpl getTestTeacherServiceImpl(){
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        teacherService.setTeacherDao(getTestTeacherDao());
        return teacherService;
    }

}
