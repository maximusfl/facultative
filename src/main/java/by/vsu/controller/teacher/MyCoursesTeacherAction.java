package by.vsu.controller.teacher;

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

public class MyCoursesTeacherAction extends Action {
    private static Logger logger = Logger.getLogger("MyCoursesStudentAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called : MyCoursesStudentAction");
        Long teacher_id = null;
        try {
            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
            RegisteredUser registeredUser = (RegisteredUser) request
                    .getSession(false)
                    .getAttribute("currentUser");
            if (registeredUser != null) {
                teacher_id = registredUserService.getTeacherIdByregUserId(registeredUser.getId());
            }
            logger.info("MyCoursesStudentAction has got teacher_id: " + teacher_id);
            CourseServise courseServise = getServiceFactory().getCourseServise();
            List<Course> myCourses = courseServise.findAllTeachersCourses(teacher_id);
            List<Course> allcourses = courseServise.findAll();
            request.setAttribute("myCourses", myCourses);
            request.setAttribute("myCoursesCount", myCourses.size());
            request.setAttribute("allCourses", allcourses);
            request.setAttribute("MyCoursesCount", myCourses.size());
            return new Forward("/teacher/teacher_courses");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
