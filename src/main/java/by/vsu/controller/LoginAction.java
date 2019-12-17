package by.vsu.controller;

import by.vsu.pojo.RegisteredUser;
import by.vsu.service.RegistredUserService;
import by.vsu.service.ServiceException;
import by.vsu.util.FactoryException;
import com.mysql.jdbc.log.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginAction extends Action {
    private static Logger logger = Logger.getLogger("LoginAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called LoginAction");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        logger.info("login: "+login+" password: "+password);
        if (login != null && password != null) {
            try {
                RegistredUserService userService = getServiceFactory().getRegistredUserService();
                RegisteredUser registeredUser = userService.findByLoginAndPassword(login, password);
                if (registeredUser != null) {

                    HttpSession session = request.getSession();
                    logger.info("created httpSession: "+session+" with userRole: "+ registeredUser.getRole());
                    session.setAttribute("currentUser", registeredUser);
                    request.setAttribute("role", registeredUser.getRole());
                    return new Forward("/index");
                } else {
                    request.setAttribute("message", "incorrect login or password");
                    return new Forward("/login");
                }
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }

        } else {
            return null;
        }
    }
}
