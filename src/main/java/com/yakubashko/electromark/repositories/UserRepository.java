package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByBasketsId(Long id);
}
