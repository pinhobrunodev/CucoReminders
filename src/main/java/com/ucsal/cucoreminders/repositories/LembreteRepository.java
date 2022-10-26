package com.ucsal.cucoreminders.repositories;

import com.ucsal.cucoreminders.entities.Lembrete;
import com.ucsal.cucoreminders.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {
    List<Lembrete> findAllByUser(User user);
}
