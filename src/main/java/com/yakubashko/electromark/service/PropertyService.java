package com.yakubashko.electromark.service;

import com.yakubashko.electromark.model.Classname;
import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Property;
import com.yakubashko.electromark.model.Typename;
import com.yakubashko.electromark.repositories.ClassnameRepository;
import com.yakubashko.electromark.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final ClassnameRepository classnameRepository;
    public  void saveProperty(Property property,Long classid)
    {
        Classname classname = classnameRepository.getById(classid);
        property.setClassname(classname);


        propertyRepository.save(property);
    }

    public List<Property> list() {
        return propertyRepository.findAll();
    }

    public List<Property> listByClassname(Long id) {

        return propertyRepository.findPropertiesByClassnameId(id);
    }

}
