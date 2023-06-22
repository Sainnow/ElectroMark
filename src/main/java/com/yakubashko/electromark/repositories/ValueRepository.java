package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueRepository extends JpaRepository<Value,Long> {

    List<Value>  getValuesByPropertyId(Long id);

    Value  findByProductIdAndPropertyId(Long productId, Long propertyId);
}
