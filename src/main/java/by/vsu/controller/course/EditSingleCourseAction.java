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

public class EditSingleCourseAction extends Action {
    private static Logger logger = Logger.getLogger("EditSingleCourseAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("called EditSingleCourseAction");
            CourseServise service = getServiceFactory().getCourseServise();
            Long id = Long.parseLong(request.getParameter("course_id"));
            logger.info("course_id:  "+id);

            Course course = service.findById(id);
            logger.info("course: "+course);
            request.setAttribute("course", course);

            return new Forward("/admin/editsinglecourse");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
    }

