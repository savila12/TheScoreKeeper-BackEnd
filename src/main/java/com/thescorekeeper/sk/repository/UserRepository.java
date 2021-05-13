package com.thescorekeeper.sk.repository;


import com.thescorekeeper.sk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // additional methods needed to register new user
    boolean existsByEmailAddress(String userEmailAddress);

    User findByEmailAddress(String userEmailAddress);

}
