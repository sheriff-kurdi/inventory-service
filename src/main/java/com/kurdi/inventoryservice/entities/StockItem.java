package com.kurdi.inventoryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock_items")
public class StockItem {
    //stock keeping unit.
    @Id
    String SKU = new GUIDGenerator().toString();
    Integer SupplierIdentity;
    String name;
    String description;
    double costPrice;
    double sellingPrice;
    boolean isDiscounted;
    double discount;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Category> categories;


}
