<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<hr class="my-4">









<ul class="nav justify-content-center">
    <li class="nav-item">
        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#addstudent">
            add student
        </button>
        <form action="${pageContext.request.contextPath}/admin/add_student" method="POST">
            <div class="modal fade" id="addstudent" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addteacher1">add new student</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">

                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">first name</label>
                                    <textarea class="form-control" name="first_name" id="exampleFormControlTextarea1" rows="1" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlTextarea1">last name</label>
                                    <textarea class="form-control" name="last_name" id="exampleFormControlTextarea2" rows="1" required></textarea>
                                </div>


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

        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#createstudentaccount">
            create student account
        </button>
        <form action="${pageContext.request.contextPath}/admin/create_student_account" method="POST">
            <div class="modal fade" id="createstudentaccount" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addstudtocourseButton">creating new student account</h5>
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

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">set login</label>
                                <textarea class="form-control" name="login" id="login" rows="1" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">set password</label>
                                <textarea class="form-control" name="password" id="password" rows="1" required></textarea>
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

<c:set var="studentsCountVar" scope="session" value="${studentsCountVar}"/>

<c:if test="${studentsCountVar > 0}">



        <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal" data-target="#removestudent">
            remove student
        </button>
</c:if>
        <form action="${pageContext.request.contextPath}/admin/remove_student" method="POST">
            <div class="modal fade" id="removestudent" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="ModalCenterTitle">remove student</h5>
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

<c:set var="studentsAccountCountVar" scope="session" value="${studentsAccountCount}"/>

<c:if test="${studentsAccountCountVar > 0}">
        <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal" data-target="#remove_student_account">
            remove student account
        </button>
</c:if>
        <form action="${pageContext.request.contextPath}/admin/remove_student_account" method="POST">
            <div class="modal fade" id="remove_student_account" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="ModalCenterTitleteach">remove student account</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <select name="student_id" class="form-control">
                                    <c:forEach var="account" items="${studentAccounts}">
                                        <option value="${account.id}">
                                                ${account.first_name} ${account.last_name}
                                        </option>
                                    </c:forEach>

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


</ul>



<hr class="my-4">

<table class="table table-hover">
    <thead class="thead-light">
    <tr>
        <th scope="col">S/N</th>
        <th scope="col">Student name</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="student" items="${students}">
        <tr>
            <th scope="row">${student.id}</th>

            <td>
                    ${student.first_name} ${student.last_name}
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>