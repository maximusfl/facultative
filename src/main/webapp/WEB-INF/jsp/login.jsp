<jsp:include page="header.jsp"></jsp:include>




<form action="${pageContext.request.contextPath}/login.html" method="POST">



    <label for="login">login</label>
    <div class="input-group mb-3">
        <input type="text" name="login" class="form-control" id="login" aria-describedby="basic-addon3" required>
    </div>
    <label for="password">password</label>
    <div class="input-group mb-3">
        <input type="text" name="password" class="form-control" id="password" aria-describedby="basic-addon3" required>
    </div>
    <button type="submit" class="btn btn-primary">signIn</button>
</form>
<h3>{message}</h3>


<jsp:include page="footer.jsp"></jsp:include>


