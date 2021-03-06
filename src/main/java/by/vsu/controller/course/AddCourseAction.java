package by.vsu.controller.course;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.Teacher;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.util.List;
import java.util.logging.Logger;

public class AddCourseAction extends Action {
    private static Logger logger = Logger.getLogger("AddCourseAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            logger.info("called AddCourseAction");
            TeacherService service = getServiceFactory().getTeacherService();
            List<Teacher> teachers = service.findAll();
            request.setAttribute("teachers", teachers);
            return new Forward("/admin/add_course");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}

