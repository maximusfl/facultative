package by.vsu.controller.student;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
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

public class MyCourseAction extends Action {
    private static Logger logger = Logger.getLogger("MyCourseAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called : MyCourseAction");
        String resume;
        Long raiting;
        Long student_id = null;
        Course course = null;
        try {
            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
            RegisteredUser registeredUser = (RegisteredUser) request
                    .getSession(false)
                    .getAttribute("currentUser");
            if (registeredUser != null) {
                student_id = registredUserService.getStudentIdByRegUserId(registeredUser.getId());
            }
            logger.info("MyCoursesStudentAction has got student_id: " + student_id);
            Long courseId = Long.parseLong(request.getParameter("course_id"));
            logger.info("courseId: " + courseId);
            CourseServise courseServise = getServiceFactory().getCourseServise();
            logger.info("tryint to find raiting... ");
            raiting = courseServise.getRaitingByStudentIdAndCourseId(student_id, courseId);
            logger.info("raiting:  " + raiting);
            logger.info("tryint to find resume... ");
            resume = courseServise.getResumeByStudentIdAndCourseId(student_id, courseId);
            logger.info("resume:  " + resume);
            course = courseServise.findById(courseId);
            TeacherService teacherService = getServiceFactory().getTeacherService();
            Teacher teacher = teacherService.findByCourseId(courseId);

            request.setAttribute("raiting", raiting);
            request.setAttribute("resume", resume);
            request.setAttribute("course", course);
            request.setAttribute("teacher", teacher);
            return new Forward("/student/single_course");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
