package com.yakubashko.electromark.controller;

import com.yakubashko.electromark.model.Classname;
import com.yakubashko.electromark.model.Typename;
import com.yakubashko.electromark.service.ClassnameService;
import com.yakubashko.electromark.service.TypenameService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ClassnameController {
    private final ClassnameService classnameService;

    @GetMapping("/classname")
    public String creatClassname() {
        return "product-property";
    }
}
