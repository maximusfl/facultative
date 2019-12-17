<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<form action="${pageContext.request.contextPath}/admin/savecourse" method="POST" >


<div class="form-group">
    <label for="exampleFormControlTextarea1">Enter course name</label>
    <textarea class="form-control" name="coursename" id="exampleFormControlTextarea1" rows="1" required></textarea>
</div>
<div class="form-group">
    <select name="teachers" class="form-control">
        <c:forEach var="teacher" items="${teachers}">
            <option>
               ${teacher.id} ${teacher.last_name}
            </option>
        </c:forEach>

    </select>

</div>


<button type="submit" class="btn btn-primary">save</button>
</form>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
