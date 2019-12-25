package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Student;
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

public class AddRaitingTeacherAction extends Action {
    private static Logger logger = Logger.getLogger("AddRaitingAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called : AddRaitingAction");

             try {
           Long courseId =Long.parseLong(request.getParameter("course_id"));
           logger.info("course_id: "+courseId);
           Long raiting =Long.parseLong(request.getParameter("raiting"));
           logger.info("raiting: "+raiting);
           String resume = request.getParameter("resume");
           logger.info("resume: "+resume);
           Long studentId = Long.parseLong(request.getParameter("student_id"));
           logger.info("student_id: "+ studentId);



            CourseServise courseServise = getServiceFactory().getCourseServise();
            courseServise.addRaiting(courseId,studentId,raiting,resume);

                 List<Student> studentsOnTheCourse = courseServise.findStudentsByCourseId(courseId);

                 Course course = courseServise.findById(courseId);




                 request.setAttribute("course_id", courseId);
                 request.setAttribute("studentsOnTheCourse", studentsOnTheCourse);
                 request.setAttribute("CurrentCourse",course);

                 return new Forward("/teacher/single_course");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
