package com.kurdi.inventoryservice.entities.categories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurdi.inventoryservice.entities.stock.StockItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_item_details")
public class CategoryDetails {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

}
