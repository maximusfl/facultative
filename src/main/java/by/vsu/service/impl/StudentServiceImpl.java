package by.vsu.service.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.StudentDao;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import by.vsu.service.ServiceException;
import by.vsu.service.StudentService;

import java.util.List;

public class StudentServiceImpl extends EnableTransactionService implements StudentService {
    StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public List<Student> findAll() throws ServiceException {
        try {
            getTransaction().start();
            List<Student> students = studentDao.findAll();
            if(students != null){
                getTransaction().commit();

            }
            return students;
        }catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public List<Student> findByCourseId(Long id) throws ServiceException{
        try {
            getTransaction().start();
            List<Student> students = studentDao.findByCourseId(id);
            if(students != null){
                getTransaction().commit();

            }
            return students;
        }catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public void save(Student student) throws ServiceException {

        try {
            getTransaction().start();
            studentDao.create(student);


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
            studentDao.delete(id);
            getTransaction().commit();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }


    }
}
