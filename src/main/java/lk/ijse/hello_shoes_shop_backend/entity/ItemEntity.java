package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private String itemCode;
    private String itemDesc;
    private String itemPic;
    private String Category;
    private double unitPriceSale;
    private double expectedProfit;
    private double profitMargin;
    private int status;
    private String supplierName;
    private double buyPrice;


    @ManyToMany(mappedBy = "buyItem")
    private List<OrderEntity> order;

    @ManyToMany
    private List<SupplierEntity> supplier;


//    @ManyToMany
//    private List<SizeEntity> size;
    @OneToMany(mappedBy = "item")
    private List<StockEntity> stock;

}
