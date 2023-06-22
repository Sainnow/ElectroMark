package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    List<Basket> findBasketByProductId(Long id);
}
