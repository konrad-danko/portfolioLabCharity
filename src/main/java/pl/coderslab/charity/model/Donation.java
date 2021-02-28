package pl.coderslab.charity.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Pole nie może być puste")
    @Digits(integer=5, fraction=0, message = "Wpisz liczbę całkowitą poniżej 100 000")
    @Positive(message = "Liczba musi być większa od zera")
    private Integer quantity; //(liczba worków)

    @Size(min = 1, message = "Należy wybrać przynajmniej 1 kategorię")
    @ManyToMany
    private List<Category> categories = new ArrayList<>(); //(powiązanie relacją do klasy Category) - dar może mieć wiele kategorii

    @ManyToOne
    @NotNull(message = "Wybierz fundację z listy")
    private Institution institution; // (obiekt typu Institution), pamiętaj o odpowiedniej adnotacji

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String street;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String city;

    @Column(nullable = false)
    @Pattern(regexp = "^\\d{2}\\-\\d{3}$", message = "Wpisz kod pocztowy w formacie 01-234")
    @NotBlank(message = "Pole nie może być puste")
    private String zipCode;

    @Column(nullable = false)
    @Pattern(regexp = "^\\d{9}$", message = "Wpisz nr telefonu w formacie 111222333")
    @NotBlank(message = "Pole nie może być puste")
    private String phoneNumber;

    @NotNull(message = "Wpisz datę odbioru darów")
    @FutureOrPresent(message = "Data nie może być z przeszłości")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate; // (LocalDate)

    @NotNull(message = "Wpisz godzinę odbioru darów")
    private LocalTime pickUpTime; //(LocalTime)

    @Size(max = 255, message = "Dopuszczalna ilość znaków wynosi 255")
    private String pickUpComment;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private DonationStatus donationStatus;

    private LocalDate created; //autoupdated by @PrePersist

    @PrePersist
    public void prePersist (){
        created = LocalDate.now();
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }
    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }
    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }
    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }
    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public DonationStatus getDonationStatus() {
        return donationStatus;
    }
    public void setDonationStatus(DonationStatus donationStatus) {
        this.donationStatus = donationStatus;
    }

    public LocalDate getCreated() {
        return created;
    }
    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", categories=" + categories +
                ", institution=" + institution +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", pickUpComment='" + pickUpComment + '\'' +
                ", user=" + user +
                ", donationStatus=" + donationStatus +
                ", created=" + created +
                '}';
    }
}