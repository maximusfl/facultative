package by.vsu.service.impl;

import by.vsu.dao.DaoException;
import by.vsu.dao.TeacherDao;

import by.vsu.pojo.Teacher;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl extends EnableTransactionService implements TeacherService {
    private TeacherDao teacherDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher findById(Long id) throws ServiceException {
        try {
            getTransaction().start();
            Teacher teacher = teacherDao.read(id);
            if(teacher != null){
                getTransaction().commit();

            }
            return teacher;
        }catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }

    }

    @Override
    public List<Teacher> findAll() throws ServiceException {
        try {
            getTransaction().start();
            List<Teacher> teachers = teacherDao.readAll();
            if(teachers != null){
                getTransaction().commit();

            }
            return teachers;
        }catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }

    }

    @Override
    public void save(Teacher teacher) throws ServiceException {

    }

    @Override
    public void delete(Long id) throws ServiceException {

    }
}
