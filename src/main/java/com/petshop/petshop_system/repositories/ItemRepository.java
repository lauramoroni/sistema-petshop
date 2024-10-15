package com.petshop.petshop_system.repositories;


import com.petshop.petshop_system.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


public interface ItemRepository extends JpaRepository<Item, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_item SET preco = aplicar_desconto(preco, :percentualDesconto) WHERE id_item = :itemId", nativeQuery = true)
    void aplicarDesconto(
            @Param("itemId") Long itemId,
            @Param("percentualDesconto") BigDecimal percentualDesconto
    );

}
