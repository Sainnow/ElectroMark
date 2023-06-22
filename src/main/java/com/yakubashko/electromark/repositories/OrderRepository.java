package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByCode(String code);
}
