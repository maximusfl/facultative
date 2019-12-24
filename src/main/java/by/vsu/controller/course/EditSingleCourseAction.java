package by.vsu.controller.course;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import by.vsu.service.CourseServise;
import by.vsu.service.ServiceException;
import by.vsu.service.StudentService;
import by.vsu.service.TeacherService;
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

            StudentService studentService = getServiceFactory().getStudentService();


            Long id = Long.parseLong(request.getParameter("course_id"));
            logger.info("course_id:  "+id);
            List<Student> students = studentService.findByCourseId(id);
            List<Student> allStudents = studentService.findAll();
            logger.info("count all Students: "+allStudents.size());
            int studentsCount = students.size();

            TeacherService teacherService = getServiceFactory().getTeacherService();
            Teacher teacher = teacherService.findByCourseId(id);



            Course course = service.findById(id);
            logger.info("course: "+course);
            request.setAttribute("course", course);
            request.setAttribute("students", students);
            request.setAttribute("teacher", teacher);
            request.setAttribute("studentsCount", studentsCount);
            request.setAttribute("allstudents", allStudents);


            List<Teacher> teachers = teacherService.findAll();

            logger.info("teachers list size:   "+teachers.size());
            request.setAttribute("teachers", teachers);



            return new Forward("/admin/editsinglecourse");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
    }

