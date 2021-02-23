<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Institution</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@ include file="adminHeader.jsp" %>

    <div class="d-flex justify-content-center">
        <div style="width:90%">
            <h2>${headerMessage}${empty institution.id ? "" : " nr "}${institution.id}</h2>
            <form:form modelAttribute="institution" method="post" class="border rounded shadow-lg">
                <div class="d-flex mt-3">
                    <div class="form-group mx-2">
                        <label for="name">Nazwa:</label>
                        <form:input path="name" id="name" class="form-control" disabled="${disabledParam}" autofocus="true"/>
                        <form:errors path="name" class="text-danger"/>
                    </div>
                    <div class="form-group mx-2 flex-grow-1">
                        <label for="description">Opis:</label>
                        <form:input path="description" id="description" class="form-control" disabled="${disabledParam}"/>
                        <form:errors path="description" class="text-danger"/>
                    </div>
                </div>
                <br>

                <%--Blok z przyciskami i "audit trial-em"--%>
                <div class="d-flex mb-4">
                    <div class="form-group">
                        <a href="/admin/institution/showAllInstitutions" class="btn btn-primary mx-3">Wróć do listy fundacji</a>
                        <a href="/admin/institution/editInstitution/${institution.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}">Edytuj</a>
                        <a href="/admin/institution/deleteInstitution/${institution.id}" class="btn btn-danger mx-3 ${delBtnVisibleParam}">Usuń</a>
                        <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}">
                    </div>
                </div>

                <form:hidden path="id"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
