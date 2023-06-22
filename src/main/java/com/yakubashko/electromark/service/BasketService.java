package com.yakubashko.electromark.service;

import com.yakubashko.electromark.model.*;
import com.yakubashko.electromark.repositories.BasketRepository;
import com.yakubashko.electromark.repositories.ProductRepository;
import com.yakubashko.electromark.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketService {
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    public void saveProductinBasket( Long productid,Long userid) {
        Basket basket = new Basket();
        Product product = productRepository.getById(productid);
        basket.setProduct(product);
        product.setNumber(product.getNumber()-1);
        productRepository.save(product);
        User user = userRepository.getById(userid);
        basket.setUser(user);
        basket.setStatus(1);
        basketRepository.save(basket);

    }


    public List<Basket> list() {
        return basketRepository.findAll();
    }

    public void deleteBasketProduct(Long id) {
       Basket basket = basketRepository.findById(id).orElse(null);
       Product product=productRepository.findProductByBasketsId(id);
       product.setNumber(product.getNumber()+1);
       productRepository.save(product);
       basketRepository.delete(basket);
    }

    public void deleteBasket(Long id) {
        List<Basket> baskets= basketRepository.findBasketByProductId(id);
        basketRepository.deleteAll(baskets);
    }

}
