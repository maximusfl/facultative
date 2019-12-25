<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="text-center">
    <hr class="my-4">
    <h3>${CurrentCourse.id}</h3>
    <hr class="my-4">
    <p>
        <button type="button" class="btn btn-outline-primary mr-1" data-toggle="modal" data-target="#addResume">
            add raiting
        </button>
    </p>

</div>





<div class="text-center">


    <div class="container py-3">
        <div class="row">

            <div class="mx-auto w-50 p-3 text-center">
                <table class="table ">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Students list</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${studentsOnTheCourse}">
                        <tr>
                            <td>
                                ${student.first_name} ${student.last_name}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>




<ul class="nav justify-content-center">

<li class="nav-item">

    <form action="${pageContext.request.contextPath}/teacher/add_raiting" method="POST">
        <input type="hidden" name="course_id" value="${CurrentCourse.id}"/>
        <div class="modal fade" id="addResume" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addteacher1">add raiting </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">

                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">balls</label>
                                <input type="number" min="0" max="100" class="form-control" name="raiting" id="exampleFormControlTextarea1" rows="1" required>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">resume</label>
                                <textarea class="form-control" name="resume" id="exampleFormControlTextarea2" rows="1" required></textarea>
                            </div>
                            <div class="form-group">
                                <select name="student_id" class="form-control">
                                    <c:forEach var="studentOnTheCourse" items="${studentsOnTheCourse}">
                                        <option value="${studentOnTheCourse.id}">
                                                ${studentOnTheCourse.first_name} ${studentOnTheCourse.last_name}
                                        </option>
                                    </c:forEach>

                                </select>
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

</ul>
${course.id}
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>