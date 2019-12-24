package by.vsu.service.impl;

import by.vsu.dao.CourseDao;
import by.vsu.dao.DaoException;
import by.vsu.pojo.Course;
import by.vsu.pojo.Student;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;

import java.util.List;


public class CourseServiseImpl extends EnableTransactionService implements CourseServise {
    private CourseDao courseDao;
    

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course findById(Long id) throws ServiceException {
        try {
            getTransaction().start();
            return courseDao.read(id);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) throws ServiceException {
        try {getTransaction().start();
            return courseDao.findStudentsByCourseId(courseId);

        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }

    }

    @Override
    public void save(Course course) throws ServiceException {
        try {
            getTransaction().start();
                courseDao.create(course);


            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            getTransaction().start();
           courseDao.delete(id);
           getTransaction().commit();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findAll() throws ServiceException {
        try {getTransaction().start();
            return courseDao.readAll();

        } catch(DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void deleteStudentByCourseId(Long courseId, Long studentId) throws ServiceException {
        try {
            getTransaction().start();
            courseDao.deleteStudentByCourseId(courseId,studentId);
            getTransaction().commit();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addStudentToCourse(Long courseId, Long studentId) throws ServiceException {
        try {
            getTransaction().start();
            courseDao.saveStudentToCourse(courseId,studentId);


            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeteacherOnCourse(Long courseId, Long teacherId) throws ServiceException {
        try {
            getTransaction().start();
            courseDao.updateTeacherOnCourse(courseId,teacherId);


            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findAllWithTeachers() throws ServiceException {
        try {getTransaction().start();
            return courseDao.readAllWithTeachers();

        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Course> findAllWithStudent(Long student_id) throws ServiceException {
        try {getTransaction().start();
            return courseDao.readAllWithStudent(student_id);

        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public Long getRaitingByStudentIdAndCourseId(Long student_id, Long courseId) throws ServiceException {
        try {
            getTransaction().start();
            return courseDao.getRaitingByStudentIdAndCourseId(student_id,courseId);

        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public String getResumeByStudentIdAndCourseId(Long student_id, Long courseId) throws ServiceException {
        try {
            getTransaction().start();
            return courseDao.getResumeByStudentIdAndCourseId(student_id,courseId);

        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findAllTeachersCourses(Long teacher_id) throws ServiceException {
        try {getTransaction().start();
            return courseDao.readAllTeachersCourses(teacher_id);

        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }

    @Override
    public void addRaiting(Long courseId, Long studentId, Long raiting, String resume) throws ServiceException {
        try {
            getTransaction().start();
            courseDao.AddRaiting(courseId,studentId,raiting,resume);


            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        }
    }
}
