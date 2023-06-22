package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.Classname;
import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Property;
import com.yakubashko.electromark.model.Typename;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PropertyRepository extends JpaRepository<Property,Long> {
    List<Property> getPropertiesByClassname(Classname classname);

    List<Property> findPropertiesByClassnameId(Long id);


}
