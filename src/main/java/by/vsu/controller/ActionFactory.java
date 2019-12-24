package by.vsu.controller;

import by.vsu.controller.course.*;
//import by.vsu.controller.registreduser.RegistredUserSaveAction;
import by.vsu.controller.student.*;
import by.vsu.controller.teacher.*;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
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
        actions.put("/logout",LogoutAction.class);
        actions.put("/admin/editcourses", EditCoursesAction.class);
        actions.put("/admin/editsinglecourse", EditSingleCourseAction.class);
        actions.put("/admin/addcourse", AddCourseAction.class);
        actions.put("/admin/savecourse", SaveCourseAction.class);
        actions.put("/allcourses", AllCoursesAction.class);
        actions.put("/teacher/view", TeacherViewAction.class);
//        actions.put("/user/save", RegistredUserSaveAction.class);
        actions.put("/admin/delstudfromcourse", RemoveStudentFromCourseAction.class);
        actions.put("/admin/addstudtocourse", AddStudentToCourseAction.class);
        actions.put("/admin/changeteacher",ChangeTeacherAction.class);
        actions.put("error",ErrorAction.class);
        actions.put("/admin/removecourse",RemoveCourseAction.class);
        actions.put("/admin/editteacherspage", EditTeachersAction.class);
        actions.put("/admin/addteacher", AddTeacherAction.class);
        actions.put("/admin/createteacheraccount", CreateTeacherAccount.class);
        actions.put("/admin/removeteacher", RemoveTeacherAction.class);
        actions.put("/admin/removeteacheraccount", RemoveTeacherAccountAction.class);
        actions.put("/admin/edit_student_page", EditStudentsAction.class);
        actions.put("/admin/addstudent", AddStudentAction.class);
        actions.put("/admin/create_student_account", CreateStudentAccountAction.class);
        actions.put("/admin/remove_student", RemoveStudentAction.class);
        actions.put("/admin/remove_student_account", RemoveStudentAccountAction.class);
        actions.put("/student/my_courses", MyCoursesStudentAction.class);
        actions.put("/student/single_course", MyCourseAction.class);
        actions.put("/student/take_course", AddMyCourseAction.class);
        actions.put("/student/remove_my_course", RemoveMyCourseAction.class);
        actions.put("/teacher/my_courses", MyCoursesTeacherAction.class);
        actions.put("/teacher/single_course", SingleCoueseTeacherAction.class);
        actions.put("/teacher/add_raiting", AddRaitingAction.class);






    }

    public static Action getAction(String url) throws ServletException{
        logger.info("called ActionFactory.getAction ");
        Class<?> action = actions.get(url);
        logger.info("try to process url: "+url);
        if( action != null){
            try {
                logger.info("will have been retun action: "+action.getSimpleName());

                return (Action) action.getDeclaredConstructor().newInstance();

            }catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e){
                throw new ServletException(e);
            }
        }else  {
            return null;
        }
    }
}
