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
    <title>Formularz zmiany hasła</title>
    <%--<link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>--%>
</head>
<body>
<%@ include file="adminHeaderDonator.jsp" %>

<div class="d-flex justify-content-center">
    <div style="width:90%">
        <section class="login-page">
            <h2>${headerMessage}</h2>
            <form method="post" action="/donator/editPassword">
                <div class="form-group">
                    <input type="password" name="oldPassword" placeholder="Obecne hasło" />
                </div>
                <div class="form-group">
                    <input type="password" name="newPassword1" placeholder="Nowe hasło" />
                </div>
                <div class="form-group">
                    <input type="password" name="newPassword2" placeholder="Powtórz nowe hasło" />
                </div>
                <div class="form-group form-group--buttons">
                    <a href="/donator/home" class="btn btn-primary mx-3" style="background-color: blue">Wróć na stronę główną</a>
                    <%--<button class="btn" type="submit" style="background-color: lawngreen">Zatwierdź</button>--%>
                    <input type="submit" value="Zatwierdź" class="btn btn-success mx-3" style="background-color: lawngreen">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </form>
        </section>
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
