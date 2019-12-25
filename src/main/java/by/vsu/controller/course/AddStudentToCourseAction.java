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

public class AddStudentToCourseAction extends Action {

    private static Logger logger = Logger.getLogger("AddStudentToCourseAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            logger.info("called AddStudentToCourseAction");
            Long courseId = Long.parseLong(request.getParameter("course_id"));
            Long studentId = Long.parseLong(request.getParameter("student_id"));
            CourseServise courseServise = getServiceFactory().getCourseServise();
            StudentService studentService = getServiceFactory().getStudentService();
            List<Student> allStidentsOnCourse = studentService.findByCourseId(courseId);
            for (Student student : allStidentsOnCourse) {
                if (student.getId().equals(studentId)) {
                    request.setAttribute("errorMessage", "this student already registered on course");
                    return new Forward("/error");
                }
            }
            courseServise.addStudentToCourse(courseId, studentId);
            Course course = courseServise.findById(courseId);
            List<Student> students = studentService.findByCourseId(courseId);
            int studentsCount = students.size();
            TeacherService teacherService = getServiceFactory().getTeacherService();
            Teacher teacher = teacherService.findByCourseId(courseId);
            List<Student> allstudents = studentService.findAll();

            logger.info("will be returned id for course: " + courseId);
            request.setAttribute("course", course);
            request.setAttribute("students", students);
            request.setAttribute("teacher", teacher);
            request.setAttribute("studentsCount", studentsCount);
            request.setAttribute("allstudents", allstudents);

            List<Teacher> teachers = teacherService.findAll();
            logger.info("teachers list size:   " + teachers.size());
            request.setAttribute("teachers", teachers);
            return new Forward("/admin/edit_single_course");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
