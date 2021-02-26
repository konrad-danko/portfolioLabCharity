<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Fundacje</title>
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
        <div style="width:95%">
            <div class="clearfix">
                <h2 class="float-left ml-3">Fundacje</h2>
                <a href="/admin/institution/addInstitution" class="float-right btn btn-success mr-3">Dodaj nową fundację</a>
            </div>

            <table class="table table-bordered table-hover table-sm shadow-lg">
                <thead class="bg-primary text-white">
                <tr>
                    <th>Nazwa</th>
                    <th>Opis</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allInstitutions}" var="institution">
                    <tr>
                        <td><a href="/admin/institution/showInstitution/${institution.id}">${institution.name}</a></td>
                        <td>${institution.description}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
