package com.kurdi.inventoryservice.repositories;

import com.kurdi.inventoryservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, String> {
}
