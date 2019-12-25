<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<form action="${pageContext.request.contextPath}/admin/save_course" method="POST">
    <div class="form-group">
        <select name="students" class="form-control">
            <c:forEach var="student" items="${students}">
                <option>
                        ${student.first_name} ${student.last_name}
                </option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">save</button>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>