package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    String sqlQueryFindAllUsersByRoleIdOrderedByLastName = "select * from users\n" +
            "where role_id=?1\n" +
            "order by last_name";
    @Query(value = sqlQueryFindAllUsersByRoleIdOrderedByLastName, nativeQuery = true)
    List<User> findAllUsersByRoleIdOrderedByLastName(int roleId);
}
