package com.kurdi.inventoryservice.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurdi.inventoryservice.entities.stock.StockItem;
import com.kurdi.inventoryservice.entities.stock.StockItemDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    String name;
    @ManyToOne
    @JoinColumn(name="parentCategory")
    Category parentCategory;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<StockItem> stockItems;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<CategoryDetails> categoryDetails;
}
