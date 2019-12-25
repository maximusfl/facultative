package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
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
import java.util.logging.Logger;

public class EditTeachersPageAction extends Action {
    private static Logger logger = Logger.getLogger("EditTeachersAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                logger.info("called AddcourseAction");
                TeacherService service = getServiceFactory().getTeacherService();
                List<Teacher> teachers = service.findAll();

                logger.info("teachers list size:   "+teachers.size());
                request.setAttribute("teachers", teachers);
                RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
                logger.info("trying to find all teacher accounts...");
                List<Teacher> teacherAccounts = registredUserService.findTeachersWithAccounts();
                logger.info("size list of teacher accounts: "+teacherAccounts.size());
                request.setAttribute("teacherAccounts", teacherAccounts);
                request.setAttribute("teachersCount", teachers.size());
                request.setAttribute("teacherAccountsCount",teacherAccounts.size());

                return new Forward("/admin/edit_teachers_page");
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
    }

