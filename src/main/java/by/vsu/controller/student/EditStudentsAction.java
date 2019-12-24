package by.vsu.controller.student;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import by.vsu.service.RegistredUserService;
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

public class EditStudentsAction extends Action {
    private static Logger logger = Logger.getLogger("EditStudentsAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            logger.info("called EditStudentsAction");
            StudentService studentService = getServiceFactory().getStudentService();
            List<Student> students = studentService.findAll();

            logger.info("students list size:   "+students.size());
            request.setAttribute("students", students);
            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
            logger.info("trying to find all students accounts...");
            List<Student> studentAccounts = registredUserService.findStudentsWithAccounts();
            logger.info("size list of students accounts: "+studentAccounts.size());
            request.setAttribute("studentAccounts", studentAccounts);

            request.setAttribute("studentsCountVar",students.size());
            request.setAttribute("studentsAccountCountVar",studentAccounts.size());

            return new Forward("/admin/edit_student_page");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
