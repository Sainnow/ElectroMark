package com.yakubashko.electromark.controller;

import com.yakubashko.electromark.model.Order;
import com.yakubashko.electromark.model.Support;
import com.yakubashko.electromark.service.OrderService;
import com.yakubashko.electromark.service.ProductService;
import com.yakubashko.electromark.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final SupportService supportService;
    private final ProductService productService;

    @GetMapping("/support")
    public String products(Model model, Principal principal) {
        model.addAttribute("support",supportService.list());
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "support";
    }
    @PostMapping("/help")
    public String products(@RequestParam("name") String name,@RequestParam("phone") String phone,@RequestParam("code") String code
                           ) {
        supportService.saveSupport(name,phone,code);
        return "redirect:/help";
    }

    @PostMapping("/support/{id}")
    public String supportStatus(@PathVariable("id") Long id) {
        supportService.supportChangeActive(id);
        return "redirect:/";
    }
    @PostMapping("/order/add")
    public String createProduct( @RequestParam("basket") List<Long> basketid, Order order) {
        orderService.saveOrder(order,basketid);
        return "redirect:/";
    }
}
