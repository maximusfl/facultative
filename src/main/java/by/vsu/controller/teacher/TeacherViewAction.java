package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Teacher;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherViewAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Long id = Long.parseLong(request.getParameter("id"));
            TeacherService teacherService = getServiceFactory().getTeacherService();
            Teacher teacher = teacherService.findById(id);

            if(teacher != null){
                request.setAttribute("teacher", teacher);
               return new Forward("/teacher/list");
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            return new Forward("/teacher/list");
        }catch (FactoryException | ServiceException e){
            throw  new ServletException(e);
        }
    }
}
