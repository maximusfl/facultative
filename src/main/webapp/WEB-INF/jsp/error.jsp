<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="d-flex justify-content-center align-items-center" style="height:500px;">

    <h3>Error... ${errorMessage}</h3>

</div>

<c:if test="${courseCount > 0}">

    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">S/N</th>
            <th scope="col">Course name</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="course" items="${coursesWithTisTeacher}">
            <tr>
                <th scope="row">${course.id}</th>

                <td>
                    <a href="${pageContext.request.contextPath}/admin/edit_single_course?course_id=${course.id}">${course.courseName}</a>
                </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>



<jsp:include page="/WEB-INF/jsp/footer.jsp"/>