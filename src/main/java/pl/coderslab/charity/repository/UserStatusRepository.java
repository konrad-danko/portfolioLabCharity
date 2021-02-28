package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
    UserStatus findById(int id);
}
