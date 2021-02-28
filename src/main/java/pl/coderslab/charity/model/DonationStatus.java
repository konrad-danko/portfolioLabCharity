package pl.coderslab.charity.model;

import javax.persistence.*;

@Entity
@Table(name = "donationStatuses")
public class DonationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String donationStatusName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDonationStatusName() {
        return donationStatusName;
    }
    public void setDonationStatusName(String donationStatusName) {
        this.donationStatusName = donationStatusName;
    }

    @Override
    public String toString() {
        return "DonationStatus{" +
                "id=" + id +
                ", donationStatusName='" + donationStatusName + '\'' +
                '}';
    }
}
