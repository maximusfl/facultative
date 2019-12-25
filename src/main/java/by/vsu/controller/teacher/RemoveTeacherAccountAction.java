package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
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

public class RemoveTeacherAccountAction extends Action {

    private static Logger logger = Logger.getLogger("RemoveTeacherAccountAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("called RemoveTeacherAccountAction");
            Long teacherId = Long.parseLong(request.getParameter("teacher_id"));
            RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();
            registredUserService.deleteByTeacherId(teacherId);
            List<Teacher> teacherAccounts = registredUserService.findTeachersWithAccounts();
            request.setAttribute("teacherAccounts", teacherAccounts);
            TeacherService teacherService = getServiceFactory().getTeacherService();
            List<Teacher> teachers = teacherService.findAll();
            request.setAttribute("teachers", teachers);
            request.setAttribute("teachersCount", teachers.size());
            request.setAttribute("teacherAccountsCount", teacherAccounts.size());
            return new Forward("/admin/edit_teachers_page");
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
