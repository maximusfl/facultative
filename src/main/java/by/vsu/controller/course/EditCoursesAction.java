package by.vsu.controller.course;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class EditCoursesAction extends Action {
    private static Logger logger = Logger.getLogger("EditCoursesAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("called EditCoursesAction");
            CourseServise service = getServiceFactory().getCourseServise();
            List<Course> courses = service.findAll();
            request.setAttribute("courses", courses);
            return new Forward("/admin/edit_courses");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
