<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Court Service</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: gray">

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/"
                   class="nav-link">Refresh</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/cases"
                   class="nav-link">List Cases</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Judges</h3>
        <hr>
        <div class="container text-right">
            <a href="JudgeView/create-user.jsp" class="btn btn-success">Appoint a new Judge</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>PhoneNumber</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${listJudges}" var="judge">

                <tr>
                    <td><c:out value="${judge.id}"/></td>
                    <td><c:out value="${judge.name}"/></td>
                    <td><c:out value="${judge.surname}"/></td>
                    <td><c:out value="${judge.email}"/></td>
                    <td><c:out value="${judge.phoneNumber}"/></td>

                    <td>
                        <a href="update?id=<c:out value="${judge.id}"/>">Update</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="?action=delete&id=<c:out value='${judge.id}' />">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="personalCase?id=<c:out value='${judge.id}' />">Cases</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>