package com.yakubashko.electromark.controller;

import com.yakubashko.electromark.model.Typename;
import com.yakubashko.electromark.repositories.TypenameRepository;
import com.yakubashko.electromark.service.TypenameService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpClient;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TypenameController {
    private final TypenameService typenameService;



    @GetMapping("/typename")
    public ModelAndView creatTypename(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/product/property", model);
    }
}
