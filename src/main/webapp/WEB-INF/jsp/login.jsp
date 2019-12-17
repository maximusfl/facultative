<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>


<div style="padding-left: 50px">
    <form action="${pageContext.request.contextPath}/login" method="POST">

        <label for="login">login</label>
        <div class="form-group w-25">
            <input type="text" name="login" class="form-control" id="login" aria-describedby="basic-addon3" required>
        </div>
        <label for="password">password</label>
        <div class="form-group w-25">
            <input type="text" name="password" class="form-control" id="password" aria-describedby="basic-addon3"
                   required>
        </div>
        <button type="submit" class="btn btn-primary">signIn</button>
    </form>

    <p>
    <h3>${message}</h3></p>
    <p>
    <h3>${param.message}</h3></p>
</div>

<jsp:include page="footer.jsp"></jsp:include>


