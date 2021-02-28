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
    <title>Formularz daru</title>
    <link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
</head>
<body>
<%@ include file="adminHeaderDonator.jsp" %>

<div class="d-flex justify-content-center">
    <div style="width:90%">
        <h2>${headerMessage} nr ${donation.id}</h2>
        <form:form modelAttribute="donation" method="post" class="border rounded shadow-lg">
            <div class="d-flex mt-3">
                <div class="form-group mx-2">
                    <label for="quantity">Ile worków:</label>
                    <form:input path="quantity" id="quantity" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="categories">Kategoria:</label>
                    <form:select path="categories" id="categories" items="${allCategories}" itemLabel="name" itemValue="id" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="institution">Fundacja:</label>
                    <form:select path="institution" id="institution" items="${allInstitutions}" itemLabel="name" itemValue="id" class="form-control" disabled="true"/>
                </div>
            </div>

            <div class="d-flex mt-3">
                <div class="form-group mx-2">
                    <label for="street">Ulica:</label>
                    <form:input path="street" id="street" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="city">Miejscowość:</label>
                    <form:input path="city" id="city" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="zipCode">Kod pocztowy:</label>
                    <form:input path="zipCode" id="zipCode" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="phoneNumber">Telefon:</label>
                    <form:input path="phoneNumber" id="phoneNumber" class="form-control" disabled="true"/>
                </div>
            </div>

            <div class="d-flex mt-3">
                <div class="form-group mx-2">
                    <label for="pickUpDate">Data odbioru:</label>
                    <form:input path="pickUpDate" id="pickUpDate" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="pickUpTime">Czas:</label>
                    <form:input path="pickUpTime" id="pickUpTime" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="pickUpComment">Uwagi:</label>
                    <form:input path="pickUpComment" id="pickUpComment" class="form-control" disabled="true"/>
                </div>
                <div class="form-group mx-2">
                    <label for="donationStatus">Status:</label>
                    <form:select path="donationStatus" id="donationStatus" items="${allDonationStatuses}" itemLabel="donationStatusName" itemValue="id" class="form-control" disabled="${disabledParam}"/>
                </div>
            </div>
            <br>
            <%--Blok z przyciskami i "audit trial-em"--%>
            <div class="d-flex mb-4">
                <div class="form-group">
                    <a href="/donator/home" class="btn btn-primary mx-3" style="background-color: blue">Wróć na stronę główną</a>
                    <a href="/donation/editDonation/${donation.id}" class="btn btn-warning mx-3 ${editBtnVisibleParam}" style="background-color: orange">Edytuj status</a>
                    <%--<a href="/donator/userDelete" class="btn btn-danger mx-3 ${delBtnVisibleParam}" style="background-color: red">Usuń</a>--%>
                    <br>
                    <br>
                    <input type="submit" value="Zatwierdź" class="btn btn-success mx-3 ${submitBtnVisibleParam}" style="background-color: lawngreen">
                </div>
            </div>

            <form:hidden path="id"/>
            <form:hidden path="quantity"/>
            <form:hidden path="categories"/>
            <form:hidden path="institution"/>
            <form:hidden path="street"/>
            <form:hidden path="city"/>
            <form:hidden path="zipCode"/>
            <form:hidden path="phoneNumber"/>
            <form:hidden path="pickUpDate"/>
            <form:hidden path="pickUpTime"/>
            <form:hidden path="pickUpComment"/>
            <form:hidden path="user"/>
            <form:hidden path="created"/>
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
