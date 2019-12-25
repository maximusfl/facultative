<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>



<div class="text-center">
    <p>
    <h3>${course.courseName}</h3></p>
    <p>
    <h3> Teacher: ${teacher.first_name} ${teacher.last_name}</h3></p>
    <p>
    <h3> Count of students: ${studentsCount} </h3></p>
</div>

<hr class="my-4">


<ul class="nav justify-content-center">
    <li class="nav-item">
        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#changeteacher">
            change teacher
        </button>
        <form action="${pageContext.request.contextPath}/admin/change_teacher" method="POST">
            <div class="modal fade" id="changeteacher" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="changecourseteacher">current teacher: ${teacher.first_name} ${teacher.last_name} </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <select name="teacher_id" class="form-control">
                                    <c:forEach var="teach" items="${teachers}">
                                        <option value="${teach.id}">
                                                ${teach.first_name} ${teach.last_name}
                                        </option>
                                    </c:forEach>
                                    <input type="hidden" name="course_id" value="${course.id}"/>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">save</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </li>
    <li class="nav-item">

        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#addstudtocourse">
            add student
        </button>
        <form action="${pageContext.request.contextPath}/admin/add_stud_to_course" method="POST">
            <div class="modal fade" id="addstudtocourse" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addstudtocourseButton">add student to this course</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <select name="student_id" class="form-control">
                                    <c:forEach var="stud" items="${allstudents}">
                                        <option value="${stud.id}">
                                                ${stud.first_name} ${stud.last_name}
                                        </option>
                                    </c:forEach>
                                    <input type="hidden" name="course_id" value="${course.id}"/>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">save</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

    </li>



    <li class="nav-item">
        <c:set var="num" scope="session" value="${studentsCount}"/>

        <c:if test="${num >= 1}">

        <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal" data-target="#exampleModalCenter">
            remove student
        </button>
        </c:if>
        <form action="${pageContext.request.contextPath}/admin/del_stud_from_course" method="POST">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle">Remove students from this course</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <select name="student_id" class="form-control">
                                    <c:forEach var="student" items="${students}">
                                        <option value="${student.id}">
                                                ${student.first_name} ${student.last_name}
                                        </option>
                                    </c:forEach>
                                    <input type="hidden" name="course_id" value="${course.id}"/>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">remove</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </li>


    <li class="nav-item">


            <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal" data-target="#removecourse">
                remove course
            </button>

        <form action="${pageContext.request.contextPath}/admin/remove_course" method="POST">
            <div class="modal fade" id="removecourse" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" name="course_id" value="${course.id}"/>
                                <h5 class="modal-title" id="ModalCenterTitle">Remove this course?</h5>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">remove</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </li>


</ul>

<hr class="my-4">


<div class="w-50 p-3">
    <table class="table table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">List students of ${course.courseName}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <th scope="row">${student.first_name} ${student.last_name}</th>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>