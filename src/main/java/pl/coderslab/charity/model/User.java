package pl.coderslab.charity.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Pole nie może być puste")
    @Email(message = "Wpisz poprawny adres email")
    @Size(max = 50, message = "Dopuszczalna ilość znaków wynosi 50")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    private String password;

    @ManyToOne
    private Role role;

    private int enabled;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }
}
