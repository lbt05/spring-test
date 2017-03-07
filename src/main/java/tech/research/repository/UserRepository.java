package tech.research.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.research.data.User;

/**
 * Created by jay on 6/03/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndAge(String username, Integer age);

    @Query("from User u where u.username=:username")
    User findUser(@Param("username") String username);
}
