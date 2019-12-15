package by.vsu.controller;

import by.vsu.controller.course.AllCoursesAction;
import by.vsu.controller.registreduser.RegistredUserSaveAction;
import by.vsu.controller.teacher.TeacherViewAction;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    private static Logger logger = Logger.getLogger("ActionFactory");

    static {
        actions.put("/", MainAction.class);
        actions.put("/index",MainAction.class);
        actions.put("/login",LoginAction.class);
        actions.put("/allcourses", AllCoursesAction.class);
        actions.put("/teacher/view", TeacherViewAction.class);
        actions.put("/user/save", RegistredUserSaveAction.class);


    }

    public static Action getAction(String url) throws ServletException{
        logger.info("called ActionFactory.getAction ");
        Class<?> action = actions.get(url);
        logger.info("try to process url: "+url);
        if( action != null){
            try {
                logger.info("will retunr action: "+action.newInstance().getClass().getSimpleName());
                return (Action) action.newInstance();
            }catch (InstantiationException | IllegalAccessException | NullPointerException e){
                throw new ServletException(e);
            }
        }else  {
            return null;
        }
    }
}
