package com.kurdi.inventoryservice.entities.stock;

import com.kurdi.inventoryservice.entities.categories.Category;
import lombok.*;
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

    double costPrice;
    @Getter(AccessLevel.NONE)
    double sellingPrice;
    boolean isDiscounted;
    double discount;
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stockItem")
    private Set<StockItemDetails> stockItemDetails;
    public double getSellingPrice(){
        if (isDiscounted){
            return sellingPrice - discount;
        }
        return sellingPrice;
    }


}
