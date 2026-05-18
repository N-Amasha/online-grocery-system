package lk.evergreen.userManagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import lk.evergreen.userManagement.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
