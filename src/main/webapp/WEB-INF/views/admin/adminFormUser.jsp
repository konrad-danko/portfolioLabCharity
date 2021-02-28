<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@ include file="adminHeaderAdmin.jsp" %>

    <div class="d-flex justify-content-center">
        <div style="width:90%">
            <h2>${headerMessage} nr ${user.id}</h2>
            <form:form modelAttribute="user" method="post" class="border rounded shadow-lg">
                <div class="d-flex mt-3">
                    <div class="form-group mx-2">
                        <label for="firstName">Imię:</label>
                        <form:input path="firstName" id="firstName" class="form-control" disabled="${disabledParam}"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="lastName">Nazwisko:</label>
                        <form:input path="lastName" id="lastName" class="form-control" disabled="${disabledParam}"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="email">Email:</label>
                        <form:input path="email" id="email" class="form-control" disabled="${disabledParam}"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="role">Rola:</label>
                        <form:select path="role" id="role" items="${allRoles}" itemLabel="roleName" itemValue="id" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="role" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="userStatus">Status:</label>
                        <form:select path="userStatus" id="userStatus" items="${allUserStatuses}" itemLabel="userStatusName" itemValue="id" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="userStatus" class="text-danger"/>
                    </div>
                </div>
                <br>

                <%--Blok z przyciskami i "audit trial-em"--%>
                <div class="d-flex mb-4">
                    <div class="form-group">
                        <a href="/admin/user/showAllUsers/1" class="btn btn-primary mx-3">Wróć do listy administratorów</a>
                        <a href="/admin/user/showAllUsers/2" class="btn btn-primary mx-3">Wróć do listy darczyńców</a>
                        <a href="/admin/user/editUser/${user.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
                        <a href="/admin/user/deleteUser/${user.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
                        <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
                    </div>
                </div>

                <form:hidden path="id"/>
                <form:hidden path="password"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
