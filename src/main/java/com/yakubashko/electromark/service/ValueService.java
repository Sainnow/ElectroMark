package com.yakubashko.electromark.service;

import com.yakubashko.electromark.model.Classname;
import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Property;
import com.yakubashko.electromark.model.Value;
import com.yakubashko.electromark.repositories.ProductRepository;
import com.yakubashko.electromark.repositories.PropertyRepository;
import com.yakubashko.electromark.repositories.ValueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValueService {
    private final ValueRepository valueRepository;
    private final PropertyRepository propertyRepository;
    private final ProductRepository productRepository;

    public  void saveValue(String values,Long propertyid,Long productid)
    {
        Value value = new Value();
        value.setValue(values);
       Property property = propertyRepository.getById(propertyid);
        value.setProperty(property);
        Product product = productRepository.getById(productid);
        value.setProduct(product);
        valueRepository.save(value);

    }

    public List<Value> list() {
        return valueRepository.findAll();
    }

    public Value listByProductAndProperty(Long productId, Long propertyId) {

        return valueRepository.findByProductIdAndPropertyId(productId,propertyId);
    }
}
