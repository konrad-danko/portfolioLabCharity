<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Form Nav</title>
    <link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
</head>
<body>

<nav class="container container--70">
    <ul class="nav--actions">
        <li class="logged-user">Witaj Agata
            <ul class="dropdown">
                <li><a href="#">Profil</a></li>
                <li><a href="#">Moje zbiórki</a></li>
                <li><a href="#">Wyloguj</a></li>
            </ul>
        </li>
    </ul>

    <ul class="nav--actions">
        <li><a href="" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li class="highlighted"><a href="<c:url value="/register/create"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>

    <ul>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
        <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="<c:url value="/donation/addDonation"/>" class="btn btn--without-border">Przekaż dary</a></li>
        <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>

</body>
</html>
