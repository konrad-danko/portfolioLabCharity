package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    String sqlQueryFindAllOrderedByName = "select * from categories\n" +
            "order by name";
    @Query(value = sqlQueryFindAllOrderedByName, nativeQuery = true)
    List<Category> findAllOrderedByName();
}
