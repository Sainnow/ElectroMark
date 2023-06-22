package com.yakubashko.electromark.service;


import com.yakubashko.electromark.model.Basket;
import com.yakubashko.electromark.model.Order;
import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Support;
import com.yakubashko.electromark.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private  final BasketRepository basketRepository;
    private final UserRepository userRepository;


    public void saveOrder(Order order, List<Long> basketid) {

        orderRepository.save(order);
        String data= String.valueOf(userRepository.findByBasketsId(basketid.get(0)).getName().charAt(0));
        order.setUser(userRepository.findByBasketsId(basketid.get(0)));
        order.setCode(data+order.getId());
        orderRepository.save(order);
        for (int i = 0; i < basketid.size(); i++) {
            Basket basket=basketRepository.getById(basketid.get(i));
            basket.setStatus(0);
            basket.setOrder(order);
            basketRepository.save(basket);
        }
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Order listOrderByCode(String code) {
        return orderRepository.findByCode(code);
    }
}
