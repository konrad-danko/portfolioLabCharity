<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <title>Register</title>
        <link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
    </head>
    <body>
        <header>
            <%@include file="headerFormNav.jsp"%>
        </header>

        <section class="login-page">
            <h2>Załóż konto</h2>

            <form:form modelAttribute="user" method="post">
                <div class="form-group">
                    <form:input path="firstName" placeholder="Imię" autofocus="true"/>
                    <span style="color:Red; font-size:1.8em"><form:errors path="firstName"/></span>
                </div>
                <div class="form-group">
                    <form:input path="lastName" placeholder="Nazwisko"/>
                    <span style="color:Red; font-size:1.8em"><form:errors path="lastName"/></span>
                </div>
                <div class="form-group">
                    <form:input path="email" placeholder="Email"/>
                    <span style="color:Red; font-size:1.8em"><form:errors path="email"/></span>
                </div>
                <div class="form-group">
                    <form:password path="password" placeholder="Hasło"/>
                    <span style="color:Red; font-size:1.8em"><form:errors path="password"/></span>
                </div>

                <form:hidden path="role"/>

                <%--<div class="form-group">
                    <input type="email" name="email" placeholder="Email" />
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="Hasło" />
                </div>
                <div class="form-group">
                    <input type="password" name="password2" placeholder="Powtórz hasło" />
                </div>--%>

                <div class="form-group form-group--buttons">
                    <a href="#loginButton" class="btn btn--without-border">Zaloguj się</a>
                    <button class="btn" type="submit">Załóż konto</button>
                </div>
            </form:form>
        </section>

        <%@include file="footer.jsp"%>

        <script src="<c:url value="../resources/js/app.js"/>"></script>
    </body>
</html>
