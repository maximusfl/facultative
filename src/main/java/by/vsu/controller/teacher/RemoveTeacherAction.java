package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RemoveTeacherAction extends Action {
    private static Logger logger = Logger.getLogger("RemoveTeacherAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            logger.info("called RemoveTeacherAction");
            Long teacherId = Long.parseLong(request.getParameter("teacher_id"));

            CourseServise courseServise = getServiceFactory().getCourseServise();

            List<Course> allCourses = courseServise.findAll();

            List<Course> coursesWithTisTeacher = new ArrayList<>();

            for(Course course : allCourses){
                if(course.getTeacher().getId().equals(teacherId)){
                    coursesWithTisTeacher.add(course);
                }
            }
            if(!coursesWithTisTeacher.isEmpty()){
                request.setAttribute("errorMessage", "this teacher has running: "+coursesWithTisTeacher.size()+" courses");
                request.setAttribute("coursesWithTisTeacher", coursesWithTisTeacher);
                request.setAttribute("courseCount", coursesWithTisTeacher.size());
                return new Forward("/error");
            }


            TeacherService teacherService = getServiceFactory().getTeacherService();
            teacherService.delete(teacherId);

            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();

            List<Teacher> teacherAccounts = registredUserService.findTeachersWithAccounts();



            request.setAttribute("teacherAccounts", teacherAccounts);
            request.setAttribute("teacherAccountsCount", teacherAccounts.size());


            List<Teacher> teachers = teacherService.findAll();
            request.setAttribute("teachers", teachers);
            request.setAttribute("teachersCount", teachers.size());

            return new Forward("/admin/edit_teachers_page");


        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
