package com.kurdi.inventoryservice.controllers;

import com.kurdi.inventoryservice.entities.StockItem;
import com.kurdi.inventoryservice.repositories.StockItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
public class StockItemsController {
    @Autowired
    StockItemsRepository stockItemsRepository;

    @GetMapping
    public List<StockItem> getAll()
    {
        return  stockItemsRepository.findAll();
    }

    @GetMapping("{sku}")
    public ResponseEntity<StockItem> getById( @PathVariable String sku)
    {

        if(!stockItemsRepository.existsById(sku))
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(stockItemsRepository.getById(sku), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StockItem> add(@RequestBody StockItem stockItem)
    {
        return new ResponseEntity<>(stockItemsRepository.save(stockItem), HttpStatus.CREATED);
    }

    @PutMapping("{sku}")
    public ResponseEntity<StockItem> update(@RequestBody StockItem stockItem, @PathVariable String sku)
    {
        if(!stockItem.getSKU().equals(sku))
        {
            return new ResponseEntity<>(stockItem, HttpStatus.BAD_REQUEST);
        }
        if(!stockItemsRepository.existsById(sku))
        {
            return new ResponseEntity<>(stockItem, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(stockItemsRepository.save(stockItem), HttpStatus.OK);
    }

    @DeleteMapping("{sku}")
    public ResponseEntity<StockItem> delete(@RequestBody StockItem stockItem, @PathVariable String sku)
    {
        if(!stockItem.getSKU().equals(sku))
        {
            return new ResponseEntity<>(stockItem, HttpStatus.BAD_REQUEST);
        }
        if(!stockItemsRepository.existsById(sku))
        {
            return new ResponseEntity<>(stockItem, HttpStatus.NOT_FOUND);

        }
        stockItemsRepository.delete(stockItem);

        return new ResponseEntity<>(stockItem, HttpStatus.OK);
    }

}
