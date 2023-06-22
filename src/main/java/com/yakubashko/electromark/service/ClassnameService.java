package com.yakubashko.electromark.service;

import com.yakubashko.electromark.model.Classname;
import com.yakubashko.electromark.model.Typename;
import com.yakubashko.electromark.repositories.ClassnameRepository;
import com.yakubashko.electromark.repositories.TypenameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassnameService {
    private final ClassnameRepository classnameRepository;
    private final TypenameRepository typenameRepository;

    public  void saveClassname(Classname classname,Long typeid)
    {
        Typename typename= typenameRepository.getById(typeid);
        classname.setTypename(typename);

        classnameRepository.save(classname);
    }
    public List<Classname> listClassname(String name) {
        if (name != null) return classnameRepository.findByName(name);
        return classnameRepository.findAll();
    }

    public Classname listClassnameByProduct(Long productId) {
        return classnameRepository.findClassnameByProductsId(productId);
    }


    public List<Classname> list() {
        return  classnameRepository.findAll();
    }
}
