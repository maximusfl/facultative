

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="text-center">
    <hr class="my-4">
    <h3>my courses</h3>
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
                            <th scope="col">Courses list</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="myCourse" items="${myCourses}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/teacher/single_course?course_id=${myCourse.id}"> ${myCourse.courseName}</a>
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


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>