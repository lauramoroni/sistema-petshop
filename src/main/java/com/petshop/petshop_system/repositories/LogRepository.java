package com.petshop.petshop_system.repositories;

import com.petshop.petshop_system.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogRepository extends JpaRepository<Log, String> {
}
