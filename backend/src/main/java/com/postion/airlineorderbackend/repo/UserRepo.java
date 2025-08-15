package com.postion.airlineorderbackend.repo;

import com.postion.airlineorderbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    // search by username
    Optional<User> findByUsername(String username);
}

