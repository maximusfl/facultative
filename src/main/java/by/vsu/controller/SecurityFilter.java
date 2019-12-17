package by.vsu.controller;

import by.vsu.pojo.RegisteredUser;
import by.vsu.pojo.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class SecurityFilter implements Filter {

    private static final Map<String, Set<Role>> permissions = new HashMap<>();
    private Logger logger = Logger.getLogger("SecurityFilter");

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> teachers = new HashSet<>();
        teachers.add(Role.TEACHER);
        Set<Role> students = new HashSet<>();
        students.add(Role.STUDENT);


        permissions.put("/logout", all);
        permissions.put("/allcourses", all);
        permissions.put("/admin/adminhomepage", admin);
        permissions.put("/",all);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
        String url = httpServletRequest.getRequestURI();
        String context = httpServletRequest.getContextPath();
        int postfixindex = url.lastIndexOf(".html");
        logger.info("called SecurityFilter");
        if(postfixindex != -1){
            logger.info("postfixIndex != -1 securityFilter prosessing url: "+url);
            url = url.substring(context.length(), postfixindex);
        }else {
            logger.info("postfixIndex -1 securityFilter prosessing url: "+url);
            url = url.substring(context.length());
        }

        Set<Role> roles = permissions.get(url);
        logger.info("roles in secFilter: "+permissions.get(url));

        if(roles != null){
            logger.info("roles in secFilter: "+roles.toArray());
            HttpSession session = httpServletRequest.getSession(false);
            if(session != null){
                RegisteredUser user = (RegisteredUser) session.getAttribute("currentUser");
                logger.info("user in secFilter: "+user);
                if(user != null && roles.contains(user.getRole())){
                    logger.info("will do process for userRole: "+user.getRole());
                    filterChain.doFilter(httpServletRequest, httpServletResponse);

                    return;
                }
            }
        } else {
            logger.info("block else filterChain.doFilter(httpServletRequest,httpServletResponse); ");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        logger.info("roles in secFilter: "+roles.toArray());

        httpServletResponse.sendRedirect(context + "/login.html?message=you do not have enough permissions");
    }

    @Override
    public void destroy() {

    }
}
