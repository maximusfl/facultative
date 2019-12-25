package by.vsu.controller.teacher;

import by.vsu.controller.Action;
import by.vsu.controller.Forward;
import by.vsu.pojo.Teacher;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;
import com.mysql.jdbc.log.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class TeachersViewAction extends Action {
    private static Logger logger = Logger.getLogger("TeacherViewAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called TeacherViewAction");
        try{
            logger.info("trying to save new teacher...");
            Teacher teacher = new Teacher();
            teacher.setFirst_name(request.getParameter("first_name"));
            teacher.setLast_name(request.getParameter("last_name"));

            TeacherService teacherService = getServiceFactory().getTeacherService();

            List<Teacher> teachers = teacherService.findAll();
            for(Teacher teacher1 : teachers){
                if(teacher1.getFirst_name().equals(teacher.getFirst_name())
                        &&
                        teacher1.getLast_name().equals(teacher.getLast_name())){
                    request.setAttribute("errorMessage", "this teacher has been added already");
                    return new Forward("/error");

                }
            }

          teacherService.save(teacher);

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
