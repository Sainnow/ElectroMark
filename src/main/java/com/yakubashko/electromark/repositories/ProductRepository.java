package com.yakubashko.electromark.repositories;


import com.yakubashko.electromark.model.Basket;
import com.yakubashko.electromark.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findProductByClassnameId(Long id);
    List<Product> findProductByTypenameId(Long id);

   Product findProductByBasketsId(Long id);
}