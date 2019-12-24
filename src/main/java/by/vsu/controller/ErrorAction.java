package by.vsu.controller;

import by.vsu.pojo.Teacher;
import by.vsu.service.ServiceException;
import by.vsu.service.TeacherService;
import by.vsu.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ErrorAction extends Action {
    private static Logger logger = Logger.getLogger("ErrorAction");
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("called ErrorAction");
        logger.info("message: "+request.getAttribute("errorMessage"));
        request.setAttribute("errorMessage", request.getAttribute("errorMessage"));

        return new Forward("/error");
    }
    }

