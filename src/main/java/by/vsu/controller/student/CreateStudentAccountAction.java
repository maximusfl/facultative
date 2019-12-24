package by.vsu.controller.student;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;
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

public class CreateStudentAccountAction extends Action {
    private static Logger logger = Logger.getLogger("CreateStudentAccount");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        logger.info("called CreateStudentAccount");
        try {


            logger.info("trying to save new StudentAccount...");

            logger.info("login: " + request.getParameter("login"));
            logger.info("password: " + request.getParameter("password"));
            logger.info("student_id: " + request.getParameter("student_id"));

            RegisteredUser registeredUser = new RegisteredUser();
            registeredUser.setRole(Role.STUDENT);
            registeredUser.setLogin(request.getParameter("login"));
            registeredUser.setPassword(request.getParameter("password"));
            Long student_id = Long.parseLong(request.getParameter("student_id"));


            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();

            if((registredUserService.findByStudentId(student_id)) != null ) {

                request.setAttribute("errorMessage", "this student has account already");
                return new Forward("/error");
            }
            if(registredUserService.findByLogin(request.getParameter("login"))!=null) {
                request.setAttribute("errorMessage", "this login is using already");
                return new Forward("/error");
            }

            registredUserService.saveStudentAccount(registeredUser,student_id );

            StudentService studentService = getServiceFactory().getStudentService();
            List<Student> students = studentService.findAll();

            logger.info("students list size:   " + students.size());
            request.setAttribute("students", students);


            List<Student> studentAccounts = registredUserService.findStudentsWithAccounts();
            request.setAttribute("studentAccounts", studentAccounts);
            request.setAttribute("studentsCountVar",students.size());
            request.setAttribute("studentsAccountCountVar",studentAccounts.size());



        } catch (NumberFormatException e) {

        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forward("/admin/edit_student_page");

    }
}
