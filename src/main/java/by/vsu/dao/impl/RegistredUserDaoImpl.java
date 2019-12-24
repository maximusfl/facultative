package by.vsu.dao.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.RegistredUserDao;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RegistredUserDaoImpl extends EnableConnectionDao implements RegistredUserDao {
    private static Logger logger = Logger.getLogger("RegistredUserDaoImpl");
    @Override
    public RegisteredUser read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`, `role` FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            RegisteredUser user = null;
            if(resultSet.next()) {
                user = new RegisteredUser();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(RegisteredUser registeredUser) throws DaoException {
        return null;
    }

    @Override
    public RegisteredUser readByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `password`, `role` FROM `registered_users` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            RegisteredUser user = null;
            if(resultSet.next()) {
                user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public RegisteredUser readByLoginAndPassword(String login, String password) throws DaoException {
        logger.info("called  readByLoginAndPassword");

        String sql = "SELECT `id`, `role` FROM `registered_users` WHERE `login` = ? AND `password` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            logger.info("resultSet: " +resultSet.toString());

            RegisteredUser user = null;
            if(resultSet.next()) {

                user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);

                user.setRole(Role.valueOf(resultSet.getString("role")));
                logger.info("role: "+resultSet.getString("role"));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public RegisteredUser readByTeacherId(Long id) throws DaoException {
        logger.info("called  readByTeacherId");

        String sql = "SELECT `id`, `role` FROM `registered_users` WHERE `teacher_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            logger.info("resultSet: " +resultSet.toString());

            RegisteredUser user = null;
            if(resultSet.next()) {
                logger.info("block if resultSet.next()...");

                user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                logger.info("role: "+resultSet.getString("role"));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void deleteByTeacherId(Long teacherId) throws DaoException {
        String sql = "DELETE FROM `registered_users` WHERE `teacher_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, teacherId);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<RegisteredUser> findAllteacherAccounts() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role` FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<RegisteredUser> users = new ArrayList<>();
            while(resultSet.next()) {
                RegisteredUser user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Teacher> findTeachersWithAccounts() throws DaoException {
        String sql = "Select teachers.id, teachers.first_name, teachers.last_name\n" +
                "From teachers Inner Join\n" +
                "  registered_users On registered_users.teacher_id = teachers.id";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Teacher> teachers = new ArrayList<>();
            while(resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong("id"));
                teacher.setFirst_name(resultSet.getString("first_name"));
                teacher.setLast_name(resultSet.getString("last_name"));

                teachers.add(teacher);
            }
            return teachers;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Student> findStudentsWithAccounts() throws DaoException {
        String sql = "Select students.id, students.first_name, students.last_name\n" +
                "From students Inner Join\n" +
                "  registered_users On registered_users.student_id = students.id";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setFirst_name(resultSet.getString("first_name"));
                student.setLast_name(resultSet.getString("last_name"));

                students.add(student);
            }
            return students;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public RegisteredUser readByStudentId(Long student_id) throws DaoException {
        logger.info("called  readByStudentId");

        String sql = "SELECT `id`, `role` FROM `registered_users` WHERE `student_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);

            statement.setLong(1, student_id);
            resultSet = statement.executeQuery();
            logger.info("resultSet: " +resultSet.toString());

            RegisteredUser user = null;
            if(resultSet.next()) {
                logger.info("block if resultSet.next()...");

                user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                logger.info("role: "+resultSet.getString("role"));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void saveStudentAccount(RegisteredUser registeredUser, Long student_id) throws DaoException {
        String sql = "INSERT INTO `registered_users` (`login`, `password`, `role`, `student_id`) VALUES (?, ?, ?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, registeredUser.getLogin());
            statement.setString(2, registeredUser.getPassword());
            statement.setString(3, registeredUser.getRole().name());
            statement.setLong(4,student_id);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void deleteByStudentId(Long studentId) throws DaoException {
        String sql = "DELETE FROM `registered_users` WHERE `student_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, studentId);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long findStudentIdByUserId(Long id) throws DaoException {
        logger.info("called  findStudentIdByUserId");

        String sql = "Select students.id \n" +
                "                From students Inner Join\n" +
                "                 `registered_users` On registered_users.student_id = students.id WHERE registered_users.`id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            logger.info("resultSet: " +resultSet.toString());

            Long studentId = null;
            if(resultSet.next()) {
                logger.info("block if resultSet.next()...");
                logger.info("result: " +resultSet.getString(1));


               studentId = Long.parseLong(resultSet.getString(1));
               logger.info("will be returhed id: "+studentId);
            }
            return studentId;
        } catch(SQLException e) {
            logger.info("exception here! :  " +e.getSQLState());
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {  logger.info("exception here! :  " +e.getLocalizedMessage());}
            try{ resultSet.close(); } catch(Exception e) {  logger.info("exception here! :  " +e.getLocalizedMessage());}
        }
    }

    @Override
    public Long findTeacherIdByUserId(Long id) throws DaoException {
        logger.info("called  findTeacherIdByUserId");

        String sql = "Select teachers.id \n" +
                "                From teachers Inner Join\n" +
                "                 `registered_users` On registered_users.teacher_id = teachers.id WHERE registered_users.`id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            logger.info("resultSet: " +resultSet.toString());

            Long teacherId = null;
            if(resultSet.next()) {
                logger.info("block if resultSet.next()...");
                logger.info("result: " +resultSet.getString(1));


                teacherId = Long.parseLong(resultSet.getString(1));
                logger.info("will be returhed id: "+teacherId);
            }
            return teacherId;
        } catch(SQLException e) {
            logger.info("exception here! :  " +e.getSQLState());
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {  logger.info("exception here! :  " +e.getLocalizedMessage());}
            try{ resultSet.close(); } catch(Exception e) {  logger.info("exception here! :  " +e.getLocalizedMessage());}
        }
    }


    @Override
    public List<RegisteredUser> readAll() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role` FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<RegisteredUser> users = new ArrayList<>();
            while(resultSet.next()) {
                RegisteredUser user = new RegisteredUser();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }




    @Override
    public void  save (RegisteredUser user, Long teacherId) throws DaoException {
        String sql = "INSERT INTO `registered_users` (`login`, `password`, `role`, `teacher_id`) VALUES (?, ?, ?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().name());
            statement.setLong(4,teacherId);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(RegisteredUser user) throws DaoException {
        String sql = "UPDATE `user` SET `login` = ?, `password` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `user` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }
}
