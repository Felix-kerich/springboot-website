package org.travel.safirilinks.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.travel.safirilinks.Models.loginModel;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<loginModel, Long> {
    Optional<loginModel> findByUsername(String username);

    Optional<loginModel> findByUsernameAndPassword(String username, String password);
}
