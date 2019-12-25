package by.vsu.controller;

import by.vsu.util.Connector;
import by.vsu.util.ServiceFactory;
import by.vsu.util.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class DispatcherServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger("DispatcherServlet");

    @Override
    public void init() throws ServletException {
        try {
            Connector.init(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/facultative?useUnicode=true&useSSL=false&characterEncoding=UTF-8",
                    "root", "root");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("called do get");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("process start!");
        String url = request.getRequestURI();
        logger.info("url in request: " + url);
        String context = request.getContextPath();
        logger.info("context in request: " + context);
        int postfixIndex = url.lastIndexOf(".jsp");
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
            logger.info("postfixIndex !=-1: url: " + url);
        } else {
            url = url.substring(context.length());
            logger.info("url without prefix! " + " url " + url);
        }
        logger.info("trying to create Action class...");
        Action action = ActionFactory.getAction(url);
        logger.info("will return Action: " + action.getClass().getSimpleName() + " by url: " + url);
        Forward forward = null;
        if (action != null) {
            try (ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory);
                forward = action.execute(request, response);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        if (forward != null && forward.isRedirect()) {
            response.sendRedirect(context + forward.getUrl());
        } else {
            if (forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            request.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(request, response);
        }
    }
}
