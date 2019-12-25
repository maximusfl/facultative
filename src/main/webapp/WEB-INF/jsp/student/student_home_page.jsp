<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<c:if test="${allCourses != null}">
    <div class="text-center">
        <hr class="my-4">

        <ul class="nav justify-content-center">

            <li class="nav-item">

                <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal"
                        data-target="#take_a_course">
                    take a course
                </button>

                <form action="${pageContext.request.contextPath}/student/take_course" method="POST">
                    <div class="modal fade" id="take_a_course" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="take_a_course_dialog">take a new course</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <select name="course_id" class="form-control">
                                            <c:forEach var="myNewCourse" items="${allCourses}">
                                                <option value="${myNewCourse.id}">
                                                        ${myNewCourse.courseName}
                                                </option>
                                            </c:forEach>

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


                <c:if test="${MyCoursesCount > 0}">


                    <button type="button" class="btn btn-outline-danger mr-1" data-toggle="modal"
                            data-target="#removeMyCourse">
                        remove my course
                    </button>
                </c:if>
                <form action="${pageContext.request.contextPath}/student/remove_my_course" method="POST">
                    <div class="modal fade" id="removeMyCourse" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="ModalCenterTitle">remove my course</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <select name="course_id" class="form-control">
                                            <c:forEach var="course" items="${myCourses}">
                                                <option value="${course.id}">
                                                        ${course.courseName}
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

    </div>

    <c:if test="${myCoursesCount > 0}">

        <div class="text-center">

            <p>
            <div class="container py-3">
                <div class="row">

                    <div class="mx-auto w-50 p-3 text-center">
                        <table class="table ">
                            <thead class="thead-light">
                            <tr>
                                <th scope="col">Course</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="myCourse" items="${myCourses}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/student/single_course?course_id=${myCourse.id}"> ${myCourse.courseName}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
        </p>
        </div>
    </c:if>

</c:if>

<c:if test="${allCourses == null}">
    <div class="jumbotron">
        <h1 class="display-4">Good luck!</h1>
        <p class="lead">The Importance of Education in the 21st Century
            HOW TO MAKE A CHOICE</p>
        <hr class="my-4">
        <p>Several educated people were educated, so educated people were highly regarded. In the modern world there are
            no problems with getting an education, at least at a basic level. It is worth noting that even now really
            educated people are very appreciated. People have ceased to appreciate the opportunity to receive it. This
            is of paramount importance.</p>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/all_courses" role="button">Learn
                more</a>
        </p>
    </div>
</c:if>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>