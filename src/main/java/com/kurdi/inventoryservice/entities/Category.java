package com.kurdi.inventoryservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
public class Category {
    @Id
    String name;
    @ManyToMany
    @JsonIgnore
    private Set<StockItem> stockItems;
}
