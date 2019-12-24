package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import by.vsu.service.CourseServise;
import by.vsu.service.RegistredUserService;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SingleCoueseTeacherAction extends Action {
    private static Logger logger = Logger.getLogger("SingleCoueseTeacherAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called : SingleCoueseTeacherAction");





        try {

            Long course_id = Long.parseLong(request.getParameter("course_id"));

            logger.info("course_id:  "+ course_id);
            CourseServise courseServise = getServiceFactory().getCourseServise();

            List<Student> studentsOnTheCourse = courseServise.findStudentsByCourseId(course_id);

            Course course = courseServise.findById(course_id);




            request.setAttribute("course_id", course_id);
            request.setAttribute("studentsOnTheCourse", studentsOnTheCourse);
            request.setAttribute("CurrentCourse",course);

            return new Forward("/teacher/single_course");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
