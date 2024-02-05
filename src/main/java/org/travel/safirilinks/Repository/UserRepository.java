package org.travel.safirilinks.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.travel.safirilinks.Models.UserLogin;
import org.travel.safirilinks.Models.loginModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserLogin,Long> {
    boolean existsByUsername(String username);
    Optional<UserLogin> findByUsername(String username);

    Optional<UserLogin> findByUsernameAndPassword(String username, String password);
}
