<%@ page import="by.vsu.pojo.RegisteredUser" %>
<%@ page import="by.vsu.pojo.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


</head>
<body>



<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active px-5">
                <a class="nav-link" href="${pageContext.request.contextPath}/all_courses">all courses<span
                        class="sr-only">(current)</span></a>
            </li>


            <c:if test="${sessionScope.currentUser.role == 'ADMIN'}">
                <li class="nav-item active px-5">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/edit_courses">course-management</a>
                </li>
                <li class="nav-item active px-5">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/edit_teachers_page">teachers-management</a>
                </li>
                <li class="nav-item active px-5">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/edit_student_page">student-management</a>
                </li>
            </c:if>



            <c:if test="${sessionScope.currentUser.role == 'STUDENT'}">
                <li class="nav-item active px-5">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/student/my_courses">my courses</a>
                </li>


            </c:if>

            <c:if test="${sessionScope.currentUser.role == 'TEACHER'}">
                <li class="nav-item active px-5">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/teacher/my_courses">my courses</a>
                </li>


            </c:if>



        </ul>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav ml-auto">
                <c:if test="${sessionScope.containsValue(currentUser)}">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">LogOut</a>
                    </li>
                </c:if>





            </ul>
        </div>
    </div>
</nav>

