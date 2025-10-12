package com.example.cricket.repository;

import com.example.cricket.model.Cricketer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketerRepository extends JpaRepository<Cricketer, Long> {
    List<Cricketer> findByNameContainingIgnoreCase(String query);
}
