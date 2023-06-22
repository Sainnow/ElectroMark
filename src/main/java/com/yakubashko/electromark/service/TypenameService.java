package com.yakubashko.electromark.service;

import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Typename;
import com.yakubashko.electromark.model.User;
import com.yakubashko.electromark.repositories.TypenameRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypenameService {
    private final TypenameRepository typenameRepository;

    public  void saveTypename(Typename typename)
    {
        typenameRepository.save(typename);
    }

    public List<Typename> listTypename(String name) {
        if (name != null) return typenameRepository.findByName(name);
        return typenameRepository.findAll();
    }

    public List<Typename> list() {
        return typenameRepository.findAll();
    }
}
