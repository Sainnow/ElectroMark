package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.Classname;
import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Typename;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassnameRepository extends JpaRepository<Classname,Long> {

   Classname findClassnameByProductsId(Long id);
    List<Classname> findByName(String name);


}
