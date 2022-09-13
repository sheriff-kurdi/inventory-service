package com.kurdi.inventoryservice.repositories;

import com.kurdi.inventoryservice.entities.stock.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemsRepository extends JpaRepository<StockItem, String> {
}
