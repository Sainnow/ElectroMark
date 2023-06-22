package com.yakubashko.electromark.repositories;

import com.yakubashko.electromark.model.Product;
import com.yakubashko.electromark.model.Typename;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypenameRepository extends JpaRepository<Typename,Long> {

    List<Typename> findByName(String name);

}
