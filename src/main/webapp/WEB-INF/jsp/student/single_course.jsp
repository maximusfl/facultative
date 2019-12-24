
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="text-center">
    <hr class="my-4">
    <h3>my results</h3>
    <hr class="my-4">

</div>




<div class="container py-3">
    <div class="row">

        <div class="mx-auto w-50 p-3 text-center">

<table class="table table-bordered">

    <c:if test="${resume == '' || resume == 'NULL'}">
        <c:set var="resume" value="course are going"></c:set>
    </c:if>

    <tbody>
    <tr>
        <th scope="row">Course</th>
        <td>${course.courseName}</td>

    </tr>
    <tr>
        <th scope="row">Teacher</th>
        <td>${teacher.first_name} ${teacher.last_name}</td>
    </tr>
    <tr>
        <th scope="row">Raiting</th>
        <td>${raiting}</td>
    </tr>
    <tr>
        <th scope="row">Resume</th>
        <td colspan="2">${resume}</td>

    </tr>
    </tbody>
</table>

        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>