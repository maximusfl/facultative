package by.vsu.controller;

import by.vsu.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {
    private ServiceFactory serviceFactory;

    public final ServiceFactory getServiceFactory(){
        return serviceFactory;
    }
    public final void setServiceFactory(ServiceFactory serviceFactory){
        this.serviceFactory = serviceFactory;
    }

    abstract public Forward execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
