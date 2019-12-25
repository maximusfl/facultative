package by.vsu.controller.course;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.Teacher;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SaveCourseAction extends Action {
    private static Logger logger = Logger.getLogger("SaveCourseAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called SaveCourseAction");
        Course course = new Course();
        Teacher teacher = new Teacher();
        try {
            String rawTeacher = request.getParameter("teachers");
            String[] strings = rawTeacher.split(" ");
            Long id = Long.parseLong(strings[0]);
            logger.info("Id: " + id);
            teacher.setId(id);
            course.setTeacher(teacher);
            course.setCourseName(request.getParameter("coursename"));
            logger.info("course_name: " + course.getCourseName());
            logger.info("teacher_id: " + teacher.getId());
            course.setTeacher(teacher);
        } catch (Exception e) {
        }

        if (course.getTeacher() != null) {
            try {
                logger.info("saving course...");
                CourseServise service = getServiceFactory().getCourseServise();
                List<Course> courses = service.findAll();
                for (Course checkingCourse : courses) {
                    if (checkingCourse.getCourseName().equals(course.getCourseName())) {
                        request.setAttribute("errorMessage", "course with the same name already exists");
                        return new Forward("/error");
                    }
                }
                service.save(course);
                courses = service.findAll();
                request.setAttribute("courses", courses);
                return new Forward("/admin/edit_courses");
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/admin/edit_courses");
    }
}

