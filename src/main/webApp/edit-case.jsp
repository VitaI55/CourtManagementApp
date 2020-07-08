<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Court Manager</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: darkorchid">
        <div>
            <a href="<%=request.getContextPath()%>/listUser" class="navbar-brand"> Users List </a>
        </div>
        <div>
            <a href="<%=request.getContextPath()%>/cases" class="navbar-brand"> Cases List</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="add-case.jsp"
                   class="nav-link">Add a new Case</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <div>
                <fieldset>
                    <form action="<%=request.getContextPath()%>/caseUpdate" method="post">
                        <caption>
                            <h2>
                                Edit Case Info
                            </h2>
                        </caption>
                        <input type="hidden" name="id" value="<c:out value='${mCase.id}' />" />
                        <fieldset class="form-group">
                            <label>Case Type</label> <label>
                            <input type="text"
                                   value="<c:out value='${mCase.caseType}' />" class="form-control"
                                   name="caseType" required="required">
                        </label>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Case Level</label> <label>
                            <input type="text"
                                   value="<c:out value='${mCase.level}' />" class="form-control"
                                   name="level" required="required">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Case Description</label> <label>
                            <input type="text"
                                   value="<c:out value='${mCase.description}' />" class="form-control"
                                   name="description">
                        </label>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Judges Id</label> <label>
                            <input type="number"
                                   value="<c:out value='${mCase.judgeId}' />" class="form-control"
                                   name="judgeId">
                        </label>
                        </fieldset>

                        <button type="submit" class="btn btn-success">Edit</button>
                    </form>
                </fieldset>
            </div>

        </div>
    </div>
</div>
</body>
</html>
