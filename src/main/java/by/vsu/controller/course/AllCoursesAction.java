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

public class AllCoursesAction extends Action {
    private static Logger logger = Logger.getLogger("AllCoursesAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

            try {
                logger.info("called AllCoursesAction");
                CourseServise service = getServiceFactory().getCourseServise();
                List<Course> courses = service.findAllWithTeachers();
                request.setAttribute("courses", courses);
                return new Forward("/allcourses");
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
    }

