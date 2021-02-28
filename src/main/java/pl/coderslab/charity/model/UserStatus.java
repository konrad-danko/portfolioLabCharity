package pl.coderslab.charity.model;

import javax.persistence.*;

@Entity
@Table(name = "userStatuses")
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String userStatusName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUserStatusName() {
        return userStatusName;
    }
    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "id=" + id +
                ", userStatusName='" + userStatusName + '\'' +
                '}';
    }
}
