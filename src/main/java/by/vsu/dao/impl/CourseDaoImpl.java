package by.vsu.dao.impl;

import by.vsu.dao.CourseDao;
import by.vsu.dao.DaoException;
import by.vsu.pojo.Course;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import com.mysql.jdbc.log.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class CourseDaoImpl extends EnableConnectionDao implements CourseDao {
    private static Logger logger = Logger.getLogger("CourseDaoImpl");


    @Override
    public Course read(Long id) throws DaoException {
        logger.info("try to find course by id...");
        String sql = "SELECT `teacher_id`, `course_name` FROM `courses` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Course course = null;
            if (resultSet.next()) {
                course = new Course();
                course.setId(id);
                Long teacherId = Long.parseLong(resultSet.getString("teacher_id"));
                Teacher teacher = new Teacher();
                teacher.setId(teacherId);
                course.setTeacher(teacher);
                course.setCourseName(resultSet.getString("course_name"));
            }
            return course;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(Course course) throws DaoException {
        logger.info("called create method CourseDao");
        String sql = "INSERT INTO `courses` (`teacher_id`, `course_name`) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, course.getTeacher().getId());

            statement.setString(2, course.getCourseName());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Course entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

        String sql = "DELETE FROM `courses` WHERE `id` = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }


    }

    @Override
    public List<Course> readAll() throws DaoException {
        logger.info("try to find list courses...");
        String sql = "SELECT `id`,`teacher_id`, `course_name` FROM `courses`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong("id"));
                logger.info("course_id: " + course.getId());
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getLong("teacher_id"));
                course.setTeacher(teacher);
                logger.info("teacher_id: " + course.getTeacher().getId());
                course.setCourseName(resultSet.getString("course_name"));
                logger.info("course_name: " + course.getCourseName());
                courses.add(course);
                logger.info("size: " + courses.size());
            }
            return courses;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void deleteStudentByCourseId(Long courseId, Long studentId) throws DaoException {
        String sql = "DELETE FROM `courses_students` WHERE (`course_id` = ?) AND (`student_id`=?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, courseId);
            statement.setLong(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void saveStudentToCourse(Long courseId, Long studentId) throws DaoException {
        logger.info("called saveStudentToCourse method CourseDao");
        String sql = "INSERT INTO `courses_students` (`course_id`, `student_id`) VALUES (?, ?)";
        PreparedStatement statement = null;
        logger.info("course id: " + courseId);
        logger.info("student id: " + studentId);
        try {
            logger.info("block try...");
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, courseId);
            statement.setLong(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("block catch...");
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                logger.info("block finaly + catch...");
            }
        }
    }

    @Override
    public void updateTeacherOnCourse(Long courseId, Long teacherId) throws DaoException {
        String sql = "UPDATE `courses` SET `teacher_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, teacherId);
            statement.setLong(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Course> readAllWithTeachers() throws DaoException {
        String sql = "Select   courses.id,courses.course_name, teachers.id,   teachers.first_name,\n" +
                "  teachers.last_name\n" +
                "From teachers Inner Join\n" +
                "  courses On courses.teacher_id = teachers.id";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course();
                Teacher teacher = new Teacher();
                course.setId(resultSet.getLong(1));
                course.setCourseName(resultSet.getString(2));
                teacher.setId(resultSet.getLong(3));
                teacher.setFirst_name(resultSet.getString(4));
                teacher.setLast_name(resultSet.getString(5));
                course.setTeacher(teacher);
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Course> readAllWithStudent(Long studentId) throws DaoException {
        logger.info("readAllWithStudent start...");
        String sql = "Select courses.*\n" +
                "From courses Inner Join\n" +
                "  courses_students On courses_students.course_id = courses.id\n" +
                "WHERE courses_students.student_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            logger.info("readAllWithStudent block try...");
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, studentId);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                logger.info("readAllWithStudent block while...");
                Course course = new Course();
                Teacher teacher = new Teacher();
                course.setId(resultSet.getLong(1));
                teacher.setId(resultSet.getLong(2));
                course.setCourseName(resultSet.getString(3));
                course.setTeacher(teacher);
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long getRaitingByStudentIdAndCourseId(Long student_id, Long courseId) throws DaoException {
        String sql = "Select raiting\n" +
                "From courses_students\n" +
                "where student_id=? AND course_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long raiting = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, student_id);
            statement.setLong(2, courseId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                logger.info("getRaitingByStudentIdAndCourseId block while...");
                if ((resultSet.getString("raiting") != null)) {
                    raiting = Long.parseLong(resultSet.getString("raiting"));
                } else raiting = 0l;
                logger.info("raiting: " + raiting);
            }
            return raiting;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }

    }

    @Override
    public String getResumeByStudentIdAndCourseId(Long student_id, Long courseId) throws DaoException {
        String sql = "Select resume\n" +
                "From courses_students\n" +
                "where student_id=? AND course_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String resume = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, student_id);
            statement.setLong(2, courseId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                logger.info("getResumeByStudentIdAndCourseId block while...");
                resume = resultSet.getString(1);
            }
            return resume;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Course> readAllTeachersCourses(Long teacher_id) throws DaoException {
        logger.info("readAllTeachersCourses start...");
        String sql = "Select * From courses \n" +
                "  where teacher_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            logger.info("readAllTeachersCourses block try...");
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, teacher_id);
            resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                logger.info("readAllTeachersCourses block while...");
                Course course = new Course();
                Teacher teacher = new Teacher();
                course.setId(resultSet.getLong(1));
                teacher.setId(resultSet.getLong(2));
                course.setCourseName(resultSet.getString(3));
                course.setTeacher(teacher);
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) throws DaoException {
        logger.info("findStudentsByCourseId start...");
        String sql = "Select id, first_name, last_name\n" +
                "From students Inner Join\n" +
                "  courses_students On courses_students.student_id = students.id\n" +
                "  WHERE course_id  = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            logger.info("findStudentsByCourseId block try...");
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, courseId);
            resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                logger.info("findStudentsByCourseId block while...");
                Student student = new Student();
                student.setId(resultSet.getLong(1));
                student.setFirst_name(resultSet.getString(2));
                student.setLast_name(resultSet.getString(3));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void AddRaiting(Long courseId, Long studentId, Long raiting, String resume) throws DaoException {
        String sql = "UPDATE `courses_students` SET `raiting` = ? , resume = ? WHERE `course_id` = ? AND student_id=?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, raiting);
            statement.setString(2, resume);
            statement.setLong(3, courseId);
            statement.setLong(4, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

}


