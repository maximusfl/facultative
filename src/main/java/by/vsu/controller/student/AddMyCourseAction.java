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

public class AddMyCourseAction extends Action {
    private static Logger logger = Logger.getLogger("AddMyCourseAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called : AddMyCourseAction");
        Long student_id = null;
             try {
          Long courseId = Long.parseLong(request.getParameter("course_id"));
                 RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();

                 RegisteredUser registeredUser = (RegisteredUser) request
                         .getSession(false)
                         .getAttribute("currentUser");

                 if(registeredUser != null){
                     student_id = registredUserService.getStudentIdByRegUserId(registeredUser.getId());
                     logger.info("student_id: "+student_id );
                     logger.info("course_id: "+courseId);
                 }
            CourseServise courseServise = getServiceFactory().getCourseServise();
                 List<Course> myCourses = courseServise.findAllWithStudent(student_id);

                 for(Course course : myCourses){
                     if (course.getId().equals(courseId)){
                         request.setAttribute("errorMessage", "yuo already have been registered on course");
                         return new Forward("/error");
                     }
                 }


                 courseServise.addStudentToCourse(courseId,student_id);
                List<Course> allcourses = courseServise.findAll();

            myCourses = courseServise.findAllWithStudent(student_id);

            request.setAttribute("myCourses", myCourses);
            request.setAttribute("myCoursesCount", myCourses.size());
            request.setAttribute("allCourses", allcourses);
            request.setAttribute("MyCoursesCount", myCourses.size());
            return new Forward("/student/student_home_page");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
