package com.petshop.petshop_system.services;

import com.petshop.petshop_system.entities.VwClienteInfo;
import com.petshop.petshop_system.repositories.VwClienteInfoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VwClienteInfoService {
    @Autowired
    VwClienteInfoRepositoy vwClienteInfoRepositoy;

    public List<VwClienteInfo> findAll() {
        return vwClienteInfoRepositoy.findAll();
    }
}
