package com.yakubashko.electromark.controller;

import com.yakubashko.electromark.model.Property;
import com.yakubashko.electromark.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;


}
