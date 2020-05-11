<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
         style="background-color: forestgreen">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/listUser"
                   class="nav-link">List Users</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/cases"
                   class="nav-link">Refresh</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Cases</h3>
        <hr>
        <div class="container text-right">

            <a href="add-case.jsp" class="btn btn-success">Add
                New Case</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>CaseType</th>
                <th>Level</th>
                <th>Description</th>
                <th>JudgeId</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${listCases}" var="mCase">

                <tr>
                    <td><c:out value="${mCase.id}" /></td>
                    <td><c:out value="${mCase.caseType}" /></td>
                    <td><c:out value="${mCase.level}" /></td>
                    <td><c:out value="${mCase.description}" /></td>
                    <td><c:out value="${mCase.judgeId}"/></td>
                    <td>

                        <a href="caseUpdate?id=<c:out value="${mCase.id}"/>">Update</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="cases?action=delete&id=<c:out value='${mCase.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
