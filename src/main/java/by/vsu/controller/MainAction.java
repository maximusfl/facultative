package by.vsu.controller;

import by.vsu.pojo.Course;
import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class MainAction extends Action {
    private static Logger logger = Logger.getLogger("MainAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        logger.info("called MainAction, session = " + session);
        if (session != null) {
            RegisteredUser user = (RegisteredUser) session.getAttribute("currentUser");
            logger.info("currentUser: " + user);
            if (user != null) {
                logger.info("userRole: " + user.getRole());
                switch (user.getRole()) {
                    case ADMIN:
                        logger.info("case: admin");
                        request.setAttribute("role", user.getRole());
                        return new Forward("/admin/admin_home_page");
                    case TEACHER:
                        logger.info("case: teacher");
                        return new Forward("/teacher/teacher_home_page");
                    case STUDENT:
                        logger.info("case: student");
                        return new Forward("/student/student_home_page");
                    default:
                        return new Forward("/login");
                }
            } else {
                logger.info("user is null, will return forward to /login rom preLAST else");
                request.setAttribute("message", "you do not have enough permissions");
                return new Forward("/login");
            }
        } else {
            logger.info("session is null will return forward to /login flom LAST else");
            return new Forward("/login");
        }
    }
}
