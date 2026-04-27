package com.fiap.meetingroom.repository;

import com.fiap.meetingroom.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}