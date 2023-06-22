package com.yakubashko.electromark.controller;

import com.yakubashko.electromark.model.User;
import com.yakubashko.electromark.service.BasketService;
import com.yakubashko.electromark.service.OrderService;
import com.yakubashko.electromark.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BasketController {
    private final UserService userService;
    private final BasketService basketService;
    private  final OrderService orderService;
    @GetMapping("/basket")
    public String basket(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("basket", basketService.list());
        model.addAttribute("order", orderService.list());
        return "basket";
    }

    @PostMapping("/basket/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        basketService.deleteBasketProduct(id);
        return "redirect:/basket";
    }
}
