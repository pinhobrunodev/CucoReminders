package com.ucsal.cucoreminders.services;

import com.ucsal.cucoreminders.entities.User;
import com.ucsal.cucoreminders.repositories.UserRepository;
import com.ucsal.cucoreminders.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {


    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = repository.findByFullName(email);
            return user;
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid User");
        }
    }



}
