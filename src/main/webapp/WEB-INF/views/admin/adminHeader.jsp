<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Admin Header</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark mt-1 rounded-lg">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a href="/" class="btn btn-info mx-2">Strona główna</a>
        </li>
        <li class="nav-item">
            <a href="/admin/institution/showAllInstitutions" class="btn btn-info mx-2">Fundacje</a>
        </li>
        <li class="nav-item">
            <a href="/admin/admin/showAllAdmins" class="btn btn-info mx-2">Administratorzy</a>
        </li>
        <li class="nav-item">
            <a href="/admin/user/showAllUsers" class="btn btn-info mx-2">Użytkownicy</a>
        </li>
    </ul>

    <h3 class="navbar-text ml-auto">Panel administratora</h3>

    <div class="dropdown mx-5">
        <button type="button" class="btn btn-primary dropdown-toggle font-weight-bold" data-toggle="dropdown">
            ${firstName}
        </button>
        <div class="dropdown-menu bg-primary">
            <a href="/report/showInvoicedTransactions" class="dropdown-item">Edytuj profil</a>
            <form action="<c:url value="/logout"/>" method="post" class="dropdown-item">
                <input class="btn btn-sm btn-danger font-weight-bold ml-auto" type="submit" value="Wyloguj">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</nav>
<br>

<script>
    $(document).ready(function(){
        $(".dropdown-toggle").dropdown();
    });
</script>
</body>
</html>
