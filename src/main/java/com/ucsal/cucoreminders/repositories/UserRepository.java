package com.ucsal.cucoreminders.repositories;

import com.ucsal.cucoreminders.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByFullName(String name);
}
