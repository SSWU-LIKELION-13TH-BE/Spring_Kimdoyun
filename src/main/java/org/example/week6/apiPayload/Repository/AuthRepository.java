package org.example.week6.apiPayload.Repository;

import org.example.week6.apiPayload.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
    boolean existsByUsername(String username);
}
