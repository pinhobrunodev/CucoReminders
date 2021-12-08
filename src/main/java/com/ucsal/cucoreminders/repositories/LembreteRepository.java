package com.ucsal.cucoreminders.repositories;

import com.ucsal.cucoreminders.entities.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {
}
