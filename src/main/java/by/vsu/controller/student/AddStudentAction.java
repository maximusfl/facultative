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

public class AddStudentAction extends Action {

        private static Logger logger = Logger.getLogger("AddStudentAction");
        @Override
        public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            logger.info("called AddStudentAction");
            try {
                logger.info("trying to save new student...");
                Student student = new Student();
                student.setFirst_name(request.getParameter("first_name"));
                student.setLast_name(request.getParameter("last_name"));

                StudentService studentService = getServiceFactory().getStudentService();

                List<Student> students = studentService.findAll();
                for (Student student1 : students) {
                    if (student1.getFirst_name().equals(student.getFirst_name())
                            &&
                            student1.getLast_name().equals(student.getLast_name())) {
                        request.setAttribute("errorMessage", "this student has been added already");
                        return new Forward("/error");

                    }
                }

                studentService.save(student);
                students = studentService.findAll();
                request.setAttribute("students", students);

                RegistredUserService registredUserService = getServiceFactory().getRegistredUserService();

                List<Student> studentAccounts = registredUserService.findStudentsWithAccounts();
                request.setAttribute("studentAccounts", studentAccounts);
                request.setAttribute("studentsCountVar",students.size());
                request.setAttribute("studentsAccountCountVar",studentAccounts.size());


                return new Forward("/admin/edit_student_page");

            } catch (NumberFormatException e) {

            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
            return null;
    }
}
