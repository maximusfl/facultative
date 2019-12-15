package by.vsu.dao;

import by.vsu.pojo.Course;
import java.util.List;

public interface CourseDao extends Dao<Course>{
    List<Course> readAll() throws DaoException;

}
