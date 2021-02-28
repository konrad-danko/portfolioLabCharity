package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;

import java.time.LocalDate;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByInstitution_Id(long id);

    List<Donation> findAllByUser_Id(long id);

    String sqlQueryGetUserDonationsSorted = "select * from donations\n" +
            "where user_id=6\n" +
            "order by\n" +
            "donation_status_id,\n" +
            "pick_up_date desc,\n" +
            "created desc";
    @Query(value = sqlQueryGetUserDonationsSorted, nativeQuery = true)
    List<Donation> getUserDonationsSorted(long id);
}
