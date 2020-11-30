package com.galvanize.accessoriesgradle.repositories;

import com.galvanize.accessoriesgradle.entities.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessoriesRepository extends JpaRepository<Accessories, Integer> {
    List<Accessories> findAccessoriesById(Integer id);
}
