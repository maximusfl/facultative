<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

<hr class="my-4">
<div class="text-center">
    <h3>current courses</h3>
</div>

<hr class="my-4">




<table class="table table-hover">
    <thead class="thead-light">
    <tr>
        <th scope="col">Course</th>
        <th scope="col">Teacher name</th>

    </tr>
    </thead>
    <tbody>

    <c:forEach var="course" items="${courses}">
        <tr>
            <th scope="row">${course.courseName}</th>

            <td>
                    ${course.teacher.first_name} ${course.teacher.last_name}
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>




<jsp:include page="footer.jsp"></jsp:include>



