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

public class RemoveStudentAccountAction extends Action {



    private static Logger logger = Logger.getLogger("RemoveStudentAccountAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            logger.info("called RemoveStudentAccountAction");
            Long studentId = Long.parseLong(request.getParameter("student_id"));

            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
            registredUserService.deleteByStudentId(studentId);



            List<Student> studentAccounts = registredUserService.findStudentsWithAccounts();
            request.setAttribute("studentAccounts", studentAccounts);





            StudentService studentService = getServiceFactory().getStudentService();






            List<Student> students = studentService.findAll();
            request.setAttribute("students", students);
            request.setAttribute("studentsCountVar",students.size());
            request.setAttribute("studentsAccountCountVar",studentAccounts.size());

            return new Forward("/admin/edit_student_page");


        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }


}
