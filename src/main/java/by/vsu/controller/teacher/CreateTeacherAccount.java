package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;
import by.vsu.pojo.Teacher;
import by.vsu.service.RegistredUserService;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CreateTeacherAccount extends Action {
    private static Logger logger = Logger.getLogger("CreateTeacherAccount");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        logger.info("called CreateTeacherAccount");
        try {


            logger.info("trying to save new TeacherAccount...");

            logger.info("login: " + request.getParameter("login"));
            logger.info("password: " + request.getParameter("password"));
            logger.info("teacher_id: " + request.getParameter("login"));
            logger.info("login: " + request.getParameter("teacher_id"));

            RegisteredUser registeredUser = new RegisteredUser();
            registeredUser.setRole(Role.TEACHER);
            registeredUser.setLogin(request.getParameter("login"));
            registeredUser.setPassword(request.getParameter("password"));
            Long teacherId = Long.parseLong(request.getParameter("teacher_id"));


            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();

            if (registredUserService.findByTeacherId(teacherId) != null ) {

                request.setAttribute("errorMessage", "this teacher has account already");
                return new Forward("/error");
            }
           if(registredUserService.findByLogin(request.getParameter("login"))!=null) {
                request.setAttribute("errorMessage", "this login is using already");
                return new Forward("/error");
            }

           registredUserService.save(registeredUser, teacherId);

            TeacherService service = getServiceFactory().getTeacherService();
            List<Teacher> teachers = service.findAll();

            logger.info("teachers list size:   " + teachers.size());
            request.setAttribute("teachers", teachers);


            List<Teacher> teacherAccounts = registredUserService.findTeachersWithAccounts();
            request.setAttribute("teacherAccounts", teacherAccounts);
            request.setAttribute("teachersCount", teachers.size());
            request.setAttribute("teacherAccountsCount",teacherAccounts.size());



        } catch (NumberFormatException e) {

        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forward("/admin/edit_teachers_page");

    }
}
