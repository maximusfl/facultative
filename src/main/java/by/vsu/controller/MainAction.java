package by.vsu.controller;

import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class MainAction extends Action {
    private static Logger logger = Logger.getLogger("MainAction");

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        logger.info("called MainAction, session = " + session);
        if (session != null) {
            RegisteredUser user = (RegisteredUser) session.getAttribute("currentUser");


            logger.info("currentUser: "+user);
            if (user != null) {
                logger.info("userRole: "+user.getRole());
                switch (user.getRole()) {
                    case ADMIN:
                        return new Forward("/registreduser/userlist");
                    case TEACHER:
                        return new Forward("/");
                    case STUDENT:
                        return new Forward("/");

                    default:
                        return new Forward("/login");
                }
            } else {
                logger.info("user is null, will return forward to /login");
                return new Forward("/login");
            }
        } else {
            logger.info("session is null will return forward to /login");
            return new Forward("/login");
        }
    }
}
