<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="text-center">
    <p>
    <h3>admin panel</h3>
    </p>
</div>

<p>
    <a class="btn btn-outline-primary btn-lg btn-block" href="${pageContext.request.contextPath}/admin/edit_courses"
       role="button">course managment</a>
</p>
<p>
    <a class="btn btn-outline-primary btn-lg btn-block"
       href="${pageContext.request.contextPath}/admin/edit_teachers_page" role="button">teacher managment</a>
</p>

<p>
    <a class="btn btn-outline-primary btn-lg btn-block"
       href="${pageContext.request.contextPath}/admin/edit_student_page" role="button">student managment</a>
</p>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>