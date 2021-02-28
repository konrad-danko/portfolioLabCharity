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
    <title>Formularz</title>
    <link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
</head>
<body>
<%@ include file="adminHeaderDonator.jsp" %>

<div class="d-flex justify-content-center">
    <div style="width:70%">
        <div class="clearfix">
            <h2 class="float-left ml-3">Moje zbiórki</h2>
        </div>

        <table class="table table-bordered table-hover table-smQQ shadow-lg">
            <thead class="bg-primary text-white">
            <tr>
                <th>Id</th>
                <th>Fundacja</th>
                <th>Data odbioru</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allDonations}" var="donation">
                <tr>
                    <td><a href="/donator/donation/showDonation/${donation.id}" style="text-decoration: underline; color: blue">${donation.id}</a></td>
                    <td>${donation.institution.getName()}</td>
                    <td>${donation.pickUpDate}</td>
                    <td>${donation.donationStatus.getDonationStatusName()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
