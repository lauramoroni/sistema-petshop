package com.petshop.petshop_system.repositories;


import com.petshop.petshop_system.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
