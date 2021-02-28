<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Formularz</title>
    <%--<link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>--%>
</head>
<body>
<%@ include file="adminHeaderDonator.jsp" %>

<div class="d-flex justify-content-center">
    <div style="width:90%">
        <h2>${headerMessage}</h2>
        <form:form modelAttribute="user" method="post" class="border rounded shadow-lg">
            <div class="d-flex mt-3">
                <div class="form-group mx-2">
                    <label for="firstName">Imię:</label>
                    <form:input path="firstName" id="firstName" class="form-control" disabled="${disabledParam}"/>
                    <form:errors path="firstName" class="text-danger"/>
                </div>
                <div class="form-group mx-2 flex-grow-1">
                    <label for="lastName">Nazwisko:</label>
                    <form:input path="lastName" id="lastName" class="form-control" disabled="${disabledParam}"/>
                    <form:errors path="lastName" class="text-danger"/>
                </div>
                <div class="form-group mx-2 flex-grow-1">
                    <label for="email">Email:</label>
                    <form:input path="email" id="email" class="form-control" disabled="${disabledParam}"/>
                    <form:errors path="email" class="text-danger"/>
                </div>
            </div>
            <br>

            <%--Blok z przyciskami i "audit trial-em"--%>
            <div class="d-flex mb-4">
                <div class="form-group">
                    <a href="/donator/home" class="btn btn-primary mx-3" style="background-color: blue">Wróć na stronę główną</a>
                    <a href="/donator/userEdit" class="btn btn-warning mx-3 ${editBtnVisibleParam}" style="background-color: orange">Edytuj</a>
                    <a href="/donator/userDelete" class="btn btn-danger mx-3 ${delBtnVisibleParam}" style="background-color: red">Usuń</a>
                    <br>
                    <br>
                    <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}" style="background-color: lawngreen">
                </div>
            </div>

            <form:hidden path="id"/>
            <form:hidden path="password"/>
            <form:hidden path="role"/>
        </form:form>
    </div>
</div>

<br>
<br>
<br>



<%@include file="../footer.jsp"%>

<script src="<c:url value="../resources/js/app.js"/>"></script>
<script src="<c:url value="../resources/js/appForm.js"/>"></script>
</body>
</html>
