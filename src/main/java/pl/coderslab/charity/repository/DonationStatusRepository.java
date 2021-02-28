package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.DonationStatus;

@Repository
public interface DonationStatusRepository extends JpaRepository<DonationStatus, Integer> {
    DonationStatus findById(int id);
}
