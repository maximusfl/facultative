package by.vsu.controller.student;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.Student;
import by.vsu.pojo.Teacher;
import by.vsu.service.*;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RemoveStudentAction extends Action {


    private static Logger logger = Logger.getLogger("RemoveStudentAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            logger.info("called RemoveStudentAction");
            Long studentId = Long.parseLong(request.getParameter("student_id"));
            logger.info("studentId: "+studentId);

            StudentService studentService = getServiceFactory().getStudentService();


            studentService.delete(studentId);

            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();

            List<Student> studentAccounts = registredUserService.findStudentsWithAccounts();
            request.setAttribute("studentAccounts", studentAccounts);



            List<Student> students = studentService.findAll();

            logger.info("students list size:   "+students.size());
            request.setAttribute("students", students);
            request.setAttribute("studentsCountVar",students.size());
            request.setAttribute("studentsAccountCountVar",studentAccounts.size());

            return new Forward("/admin/edit_student_page");


        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }


}
