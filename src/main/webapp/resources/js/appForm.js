document.addEventListener("DOMContentLoaded", function () {

    const finalNextButtonElement = document.getElementById("finalNextButton");
    function insertSummaryInfo(event){

        let numberOfBagsInputElement = document.getElementById("numberOfBags");
        let numberOfBags = numberOfBagsInputElement.value;

        let arrCategories = Array.from(document.getElementsByClassName("categoryDescription"));
        let categoriesNames = "";
        arrCategories.forEach(function (element){
            if(element.parentElement.firstElementChild.checked){
                categoriesNames = categoriesNames + element.innerText + ", ";
            }
        });
        if(categoriesNames.length>0){
            categoriesNames = "(" + categoriesNames.trim().substring(0, categoriesNames.length-2) + ")";
        }

        let bagsAndCategories = (numberOfBags + " " + categoriesNames).trim();

        let arrInstitutions = Array.from(document.getElementsByClassName("institutionTitle"));
        let institutionName = "";
        arrInstitutions.forEach(function (element){
            if(element.parentElement.parentElement.firstElementChild.checked){
                institutionName = element.innerText;
            }
        });

        let donationStreet = document.getElementById("donationStreet").value;
        let donationCity = document.getElementById("donationCity").value;
        let donationZipCode = document.getElementById("donationZipCode").value;
        let donationPhoneNumber = "tel. " + document.getElementById("donationPhoneNumber").value;
        let donationPickUpDate = document.getElementById("donationPickUpDate").value;
        let donationPickUpTime = document.getElementById("donationPickUpTime").value;
        let donationPickUpComment = document.getElementById("donationPickUpComment").value;

        document.getElementById("bagsAndCategoriesTargetElement").innerText = bagsAndCategories;
        document.getElementById("institutionNameTargetElement").innerText = institutionName;
        document.getElementById("donationStreetTargetElement").innerText = donationStreet;
        document.getElementById("donationCityTargetElement").innerText = donationCity;
        document.getElementById("donationZipCodeTargetElement").innerText = donationZipCode;
        document.getElementById("donationPhoneNumberTargetElement").innerText = donationPhoneNumber;
        document.getElementById("donationPickUpDateTargetElement").innerText = donationPickUpDate;
        document.getElementById("donationPickUpTimeTargetElement").innerText = donationPickUpTime;
        document.getElementById("donationPickUpCommentTargetElement").innerText = donationPickUpComment;
    }

    insertSummaryInfo();
    finalNextButtonElement.addEventListener("click", insertSummaryInfo);

});
