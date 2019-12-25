package by.vsu.controller.course;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;
import by.vsu.service.StudentService;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class RemoveCourseAction extends Action {
    private static Logger logger = Logger.getLogger("RemoveCourseAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("called RemoveCourseAction");
            Long courseId = Long.parseLong(request.getParameter("course_id"));
            CourseServise courseServise = getServiceFactory().getCourseServise();
            logger.info("course_id: " + courseId);
            courseServise.delete(courseId);
            List<Course> courses = courseServise.findAll();
            request.setAttribute("courses", courses);
            return new Forward("/admin/edit_courses");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
