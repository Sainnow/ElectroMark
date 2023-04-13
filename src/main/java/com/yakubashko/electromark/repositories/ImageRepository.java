package com.yakubashko.electromark.repositories;


import com.yakubashko.electromark.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}