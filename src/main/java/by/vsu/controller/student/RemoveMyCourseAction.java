package by.vsu.controller.student;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
import by.vsu.service.CourseServise;
import by.vsu.service.RegistredUserService;
import by.vsu.service.ServiceException;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class RemoveMyCourseAction extends Action {
    private static Logger logger = Logger.getLogger("RemoveMyCourseAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long student_id = null;
        try {
            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
            RegisteredUser registeredUser = (RegisteredUser) request
                    .getSession(false)
                    .getAttribute("currentUser");
            if (registeredUser != null) {
                student_id = registredUserService.getStudentIdByRegUserId(registeredUser.getId());
            }
            logger.info("MyCoursesStudentAction has got student_id: " + student_id);
            logger.info("called RemoveMyCourseAction");
            Long courseId = Long.parseLong(request.getParameter("course_id"));
            CourseServise courseServise = getServiceFactory().getCourseServise();
            logger.info("course_id: " + courseId);
            courseServise.deleteStudentByCourseId(courseId, student_id);
            List<Course> myCourses = courseServise.findAllWithStudent(student_id);
            List<Course> allcourses = courseServise.findAll();

            request.setAttribute("myCourses", myCourses);
            request.setAttribute("myCoursesCount", myCourses.size());
            request.setAttribute("allCourses", allcourses);
            request.setAttribute("MyCoursesCount", myCourses.size());
            return new Forward("/student/student_home_page");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
