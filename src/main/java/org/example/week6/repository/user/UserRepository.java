package org.example.week6.repository.user;

import org.example.week6.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 특정 사용자 ID로 사용자를 조회하는 메서드
    Optional<User> findByUserId(String userId);
}
