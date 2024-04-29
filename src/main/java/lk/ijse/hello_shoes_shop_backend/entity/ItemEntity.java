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
    @Column(columnDefinition = "LONGTEXT")
    private String itemPic;
    private String Category;
    private double unitPriceSale;
    private double expectedProfit;
    private double profitMargin;
    private int status;
    private double buyPrice;


    @ManyToMany(mappedBy = "buyItem",cascade = CascadeType.DETACH)
    private List<OrderEntity> order;







    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "supplier_supply_item_details"
    )
    private List<SupplierEntity> supplierEntityList;


    //
    @OneToMany(mappedBy = "itemEntititys")
    private List<StockEntity> stockEntityList;

//    @OneToMany(mappedBy = "item")
//    private List<StockEntity> stock;




}
