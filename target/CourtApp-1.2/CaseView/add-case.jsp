<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register user</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar-nav"
         style="background-color: yellow">
        <div>
            <a href="<%=request.getContextPath()%>/judges" class="navbar-brand"> Judges List </a>
        </div>
        <div>
            <a href="<%=request.getContextPath()%>/cases" class="navbar-brand"> Cases List</a>
        </div>
    </nav>
</header>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <div>
                <fieldset>
                    <form action="<%=request.getContextPath()%>/create-case" method="post">
                        <caption>
                            <h2>
                                Add a New Case
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
                                   value = "<c:out value='${mCase.description}' />" class="form-control"
                                   name="description">
                        </label>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Judges ID</label>
                            <input type="number"
                                   value = "<c:out value='${mCase.judgeId}' />" class="form-control"
                                   name="judgeId">
                        </fieldset>
                        <button type="submit" class="btn btn-success">Add</button>
                    </form>
                </fieldset>
            </div>

        </div>
    </div>
</div>
</body>
</html>
