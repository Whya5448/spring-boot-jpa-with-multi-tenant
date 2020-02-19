package com.example.demo.config;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, UUID> {

}