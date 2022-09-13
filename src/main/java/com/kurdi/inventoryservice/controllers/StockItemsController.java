package com.kurdi.inventoryservice.controllers;

import com.kurdi.inventoryservice.entities.stock.StockItem;
import com.kurdi.inventoryservice.repositories.StockItemsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/inventory/")
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
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<StockItem> add(@RequestBody StockItem stockItem)
    {   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer identity = Integer.parseInt(auth.getPrincipal().toString());
        stockItem.setSupplierIdentity(identity);
        return new ResponseEntity<>(stockItemsRepository.save(stockItem), HttpStatus.CREATED);
    }

    @PutMapping("{sku}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer identity = Integer.parseInt(auth.getPrincipal().toString());
        if(!stockItemsRepository.getById(sku).getSupplierIdentity().equals(identity))
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(stockItemsRepository.save(stockItem), HttpStatus.OK);
    }

    @DeleteMapping("{sku}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<StockItem> delete(@PathVariable String sku)
    {
        if(!stockItemsRepository.existsById(sku))
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        StockItem stockItem = stockItemsRepository.getById(sku);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer identity = Integer.parseInt(auth.getPrincipal().toString());
        if(!stockItemsRepository.getById(sku).getSupplierIdentity().equals(identity))
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }
        stockItemsRepository.delete(stockItem);

        return new ResponseEntity<>(stockItem, HttpStatus.OK);
    }

}
