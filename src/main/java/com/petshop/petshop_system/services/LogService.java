package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.Log;
import com.petshop.petshop_system.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public List<Log> findAll() {
        return logRepository.findAll();
    }
}
