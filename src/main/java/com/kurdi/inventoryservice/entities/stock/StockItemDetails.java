package com.kurdi.inventoryservice.entities.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "stock_item_details")
public class StockItemDetails {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stock_item_id")
    StockItem stockItem;

}
