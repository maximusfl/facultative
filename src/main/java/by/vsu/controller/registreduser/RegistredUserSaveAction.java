//package by.vsu.controller.registreduser;
//
//import by.vsu.controller.Action;
//import by.vsu.controller.Forward;
//import by.vsu.pojo.RegisteredUser;
//import by.vsu.pojo.Role;
//import by.vsu.service.RegistredUserService;
//import by.vsu.service.ServiceException;
//import by.vsu.util.FactoryException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class RegistredUserSaveAction extends Action {
//    @Override
//    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RegisteredUser user = new RegisteredUser();
//        try {
////            user.setId(Long.parseLong(req.getParameter("id")));
//        } catch(NumberFormatException e) {}
//        user.setLogin(req.getParameter("login"));
//        try {
//            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
//        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
//        if(user.getLogin() != null && user.getRole() != null) {
//            try {
//                RegistredUserService service = getServiceFactory().getRegistredUserService();
//                service.save(user,req.getParameter("id"));
//            } catch(FactoryException | ServiceException e) {
//                throw new ServletException(e);
//            }
//        }
//        return new Forward("/user/list.html");
//    }
//}
