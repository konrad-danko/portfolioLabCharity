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
    <header class="header--form-page">

        <%@include file="headerFormNav.jsp"%>

        <div class="slogan container container--90">
            <div class="slogan--item">
                <h1>
                    Oddaj rzeczy, których już nie chcesz<br />
                    <span class="uppercase">potrzebującym</span>
                </h1>

                <div class="slogan--steps">
                    <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                    <ul class="slogan--steps-boxes">
                        <li><div><em>1</em><span>Wybierz rzeczy</span></div></li>
                        <li><div><em>2</em><span>Spakuj je w worki</span></div></li>
                        <li><div><em>3</em><span>Wybierz fundację</span></div></li>
                        <li><div><em>4</em><span>Zamów kuriera</span></div></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>

    <section class="form--steps">
        <div class="form--steps-instructions">
            <div class="form--steps-container">
                <h3>Ważne!</h3>
                <p data-step="1" class="active">Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy wiedzieć komu najlepiej je przekazać.</p>
                <p data-step="2">Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy wiedzieć komu najlepiej je przekazać.</p>
                <p data-step="3">Wybierz jedną, do której trafi Twoja przesyłka.</p>
                <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
            </div>
        </div>

        <div class="form--steps-container">
            <div class="form--steps-counter">Krok <span>1</span>/4</div>

            <form:form modelAttribute="donation" method="post">

                <!-- STEP 1: class .active is switching steps -->
                <div data-step="1" class="active">
                    <h3>Zaznacz co chcesz oddać:</h3>
                    <p style="color:Red; font-size:1.8em"><form:errors path="categories"/></p>
                    <c:forEach items="${allCategories}" var="item">
                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="categories" value="${item.id}"
                                    ${donation.categories.contains(item) ? "checked" : ""}/>
                                <span class="checkbox"></span>
                                <span class="description categoryDescription">${item.name}</span>
                                <form:checkbox path="categories" value="${item.id}"/>
                            </label>
                        </div>
                    </c:forEach>
                    <div class="form-group form-group--buttons">
                        <button type="button" class="btn next-step">Dalej</button>
                    </div>
                </div>

                <!-- STEP 2 -->
                <div data-step="2">
                    <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>
                    <p style="color:Red; font-size:1.8em"><form:errors path="quantity"/></p>
                    <div class="form-group form-group--inline">
                        <label>
                            Liczba 60l worków:
                            <form:input path="quantity" type="number" step="1" min="1" id="numberOfBags"/>
                        </label>
                    </div>
                    <div class="form-group form-group--buttons">
                        <button type="button" class="btn prev-step">Wstecz</button>
                        <button type="button" class="btn next-step">Dalej</button>
                    </div>
                </div>

                <!-- STEP 3 -->
                <div data-step="3">
                    <h3>Wybierz organizację, której chcesz pomóc:</h3>
                    <p style="color:Red; font-size:1.8em"><form:errors path="institution"/></p>
                    <c:forEach items="${allInstitutions}" var="item">
                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="radio" name="institution" value="${item.id}"
                                    ${donation.institution.equals(item) ? "checked" : ""}/>
                                <span class="checkbox radio"></span>
                                <span class="description">
                                    <div class="title institutionTitle">${item.name}</div>
                                    <div class="subtitle">${item.description}</div>
                                </span>
                                <form:radiobutton path="institution" value="${item.id}"/>
                            </label>
                        </div>
                    </c:forEach>
                    <div class="form-group form-group--buttons">
                        <button type="button" class="btn prev-step">Wstecz</button>
                        <button type="button" class="btn next-step">Dalej</button>
                    </div>
                </div>

                <!-- STEP 4 -->
                <div data-step="4">
                    <h3>Podaj adres oraz termin odbioru rzeczy przez kuriera:</h3>
                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru</h4>
                            <div class="form-group form-group--inline">
                                <label> Ulica <form:input path="street" id="donationStreet"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="street"/></span>
                            </div>
                            <div class="form-group form-group--inline">
                                <label> Miasto <form:input path="city" id="donationCity"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="city"/></span>
                            </div>
                            <div class="form-group form-group--inline">
                                <label> Kod pocztowy <form:input path="zipCode" id="donationZipCode"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="zipCode"/></span>
                            </div>
                            <div class="form-group form-group--inline">
                                <label> Numer telefonu <form:input path="phoneNumber" id="donationPhoneNumber"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="phoneNumber"/></span>
                            </div>
                        </div>
                        <div class="form-section--column">
                            <h4>Termin odbioru</h4>
                            <div class="form-group form-group--inline">
                                <label> Data <form:input type="date" path="pickUpDate" id="donationPickUpDate"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="pickUpDate"/></span>
                            </div>
                            <div class="form-group form-group--inline">
                                <label> Godzina <form:input type="time" path="pickUpTime" id="donationPickUpTime"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="pickUpTime"/></span>
                            </div>
                            <div class="form-group form-group--inline">
                                <label> Uwagi dla kuriera <form:textarea path="pickUpComment" id="donationPickUpComment" rows="5"/> </label>
                                <span style="color:Red; font-size:1.8em"><form:errors path="pickUpComment"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-group--buttons">
                        <button type="button" class="btn prev-step">Wstecz</button>
                        <button type="button" class="btn next-step" id="finalNextButton">Dalej</button>
                    </div>
                </div>

                <!-- STEP 5 -->
                <div data-step="5">
                    <h3>Podsumowanie Twojej darowizny</h3>

                    <div class="summary">
                        <div class="form-section">
                            <h4>Oddajesz:</h4>
                            <ul>
                                <li>
                                    <span class="icon icon-bag"></span>
                                    <span class="summary--text" id="bagsAndCategoriesTargetElement">??</span>
                                </li>

                                <li>
                                    <span class="icon icon-hand"></span>
                                    <span class="summary--text" id="institutionNameTargetElement">??</span>
                                </li>
                            </ul>
                        </div>

                        <div class="form-section form-section--columns">
                            <div class="form-section--column">
                                <h4>Adres odbioru:</h4>
                                <ul>
                                    <li id="donationStreetTargetElement">??</li>
                                    <li id="donationCityTargetElement">??</li>
                                    <li id="donationZipCodeTargetElement">??</li>
                                    <li id="donationPhoneNumberTargetElement">??</li>
                                </ul>
                            </div>

                            <div class="form-section--column">
                                <h4>Termin odbioru:</h4>
                                <ul>
                                    <li id="donationPickUpDateTargetElement">??</li>
                                    <li id="donationPickUpTimeTargetElement">??</li>
                                    <li id="donationPickUpCommentTargetElement">??</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="form-group form-group--buttons">
                        <button type="button" class="btn prev-step">Wstecz</button>
                        <button type="submit" class="btn">Potwierdzam</button>
                    </div>
                </div>
            </form:form>
        </div>
    </section>

    <%@include file="footer.jsp"%>

    <script src="<c:url value="../resources/js/app.js"/>"></script>
    <script src="<c:url value="../resources/js/appForm.js"/>"></script>
    </body>
</html>
