<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<hr class="my-4">

<ul class="nav justify-content-center">
    <li class="nav-item">
        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#addteacher">
            add teacher
        </button>
        <form action="${pageContext.request.contextPath}/admin/add_teacher" method="POST">
        <div class="modal fade" id="addteacher" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addteacher1">add new teacher</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">first name</label>
                                <input type="text" class="form-control" name="first_name" id="exampleFormControlTextarea1" rows="1" required>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">last name</label>
                                <input type="text" class="form-control" name="last_name" id="exampleFormControlTextarea2" rows="1" required></input>
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
        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#createteacheraccount">
            create teacher account
        </button>
        <form action="${pageContext.request.contextPath}/admin/create_teacher_account" method="POST">
            <div class="modal fade" id="createteacheraccount" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addstudtocourseButton">creating new teacher account</h5>
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
<c:set var="teachersCountVar" scope="session" value="${teachersCount}"/>
<c:if test="${teachersCountVar > 0}">
        <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal" data-target="#removeteacher">
            remove teacher
        </button>
</c:if>
        <form action="${pageContext.request.contextPath}/admin/remove_teacher" method="POST">
            <div class="modal fade" id="removeteacher" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="ModalCenterTitle">Remove teacher</h5>
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
<c:set var="teacherAccountsCountVar" scope="session" value="${teacherAccountsCount}"/>
<c:if test="${teacherAccountsCountVar > 0}">
        <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal" data-target="#removeteacheraccount">
            remove teacher account
        </button>
</c:if>
        <form action="${pageContext.request.contextPath}/admin/remove_teacher_account" method="POST">
            <div class="modal fade" id="removeteacheraccount" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="ModalCenterTitleteach">remove teacher account</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <select name="teacher_id" class="form-control">
                                    <c:forEach var="account" items="${teacherAccounts}">
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
        <th scope="col">Teacher name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teacher" items="${teachers}">
        <tr>
            <th scope="row">${teacher.id}</th>
            <td>
               ${teacher.first_name} ${teacher.last_name}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>