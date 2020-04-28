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
         style="background-color: deepskyblue">
        <div>
            <a href="<%=request.getContextPath()%>/listUser" class="navbar-brand"> Judges List </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="create-user.jsp"
                   class="nav-link">Appoint a Judge</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <div>
                <fieldset>
                    <form action="<%=request.getContextPath()%>/update" method="post">
                        <caption>
                            <h2>
                                Change Judge Info
                            </h2>
                        </caption>
                        <input type="hidden" name="id" value="<c:out value='${judge.id}' />" />
                        <fieldset class="form-group">
                            <label>Judge Name</label> <label>
                            <input type="text"
                                   value="<c:out value='${judge.name}' />" class="form-control"
                                   name="name" required="required">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Judge Surname</label> <label>
                            <input type="text"
                                   value="<c:out value='${judge.surname}' />" class="form-control"
                                   name="surname">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Judge Email</label> <label>
                            <input type="text"
                                   value="<c:out value='${judge.email}' />" class="form-control"
                                   name="email">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Judge phoneNumber</label> <label>
                            <input type="number"
                                   value = "<c:out value='${judge.phoneNumber}' />" class="form-control"
                                   name="phoneNumber">
                        </label>
                        </fieldset>
                        <button type="submit" class="btn btn-success">Confirm Changes</button>
                    </form>
                </fieldset>
            </div>

        </div>
    </div>
</div>
</body>
</html>