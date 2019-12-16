<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="text-center">
<p>
<h3  >running courses <a class="btn float-right btn-primary" href="${pageContext.request.contextPath}/admin/addcourse.html" role="button">add new course</a></h3>
    </p>
</div>
<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">S/N</th>
        <th scope="col">Course name</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="course" items="${courses}">
        <tr>
            <th scope="row">${course.id}</th>

            <td>
                <a href="${pageContext.request.contextPath}/admin/editsinglecourse.html?course_id=${course.id}">${course.courseName}</a>
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>