package by.vsu.controller;

import by.vsu.controller.course.*;

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
        actions.put("/admin/edit_courses", EditCoursesPageAction.class);
        actions.put("/admin/edit_single_course", EditSingleCourseAction.class);
        actions.put("/admin/add_course", AddCourseAction.class);
        actions.put("/admin/save_course", SaveCourseAction.class);
        actions.put("/all_courses", AllCoursesAction.class);
        actions.put("/teacher/view", TeachersViewAction.class);
        actions.put("/admin/del_stud_from_course", RemoveStudentFromCourseAction.class);
        actions.put("/admin/add_stud_to_course", AddStudentToCourseAction.class);
        actions.put("/admin/change_teacher", ChangeTeacherOnCourseAction.class);
        actions.put("error",ErrorAction.class);
        actions.put("/admin/remove_course",RemoveCourseAction.class);
        actions.put("/admin/edit_teachers_page", EditTeachersPageAction.class);
        actions.put("/admin/add_teacher", AddTeacherAction.class);
        actions.put("/admin/create_teacher_account", CreateAccountTeacherAction.class);
        actions.put("/admin/remove_teacher", RemoveTeacherAction.class);
        actions.put("/admin/remove_teacher_account", RemoveAccountTeacherAction.class);
        actions.put("/admin/edit_student_page", EditStudentsAction.class);
        actions.put("/admin/add_student", AddStudentAction.class);
        actions.put("/admin/create_student_account", CreateAccountStudentAction.class);
        actions.put("/admin/remove_student", RemoveStudentAction.class);
        actions.put("/admin/remove_student_account", RemoveAccountStudentAction.class);
        actions.put("/student/my_courses", MyCoursesStudentAction.class);
        actions.put("/student/single_course", MyCourseStudentAction.class);
        actions.put("/student/take_course", AddMyCourseStudentAction.class);
        actions.put("/student/remove_my_course", RemoveMyCourseStudentAction.class);
        actions.put("/teacher/my_courses", MyCoursesTeacherAction.class);
        actions.put("/teacher/single_course", SingleCoueseTeacherAction.class);
        actions.put("/teacher/add_raiting", AddRaitingTeacherAction.class);






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
