package kr.co.greenuniversity.repository.user;

import kr.co.greenuniversity.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    long countById(String id);
    long countByEmail(String email);
    long countByPhone(String phone);

    Optional<User> findByEmail(String email);
}