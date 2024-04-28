package lk.ijse.hello_shoes_shop_backend.Dto;

import lk.ijse.hello_shoes_shop_backend.entity.OrderEntity;

import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemDto {
    private String itemCode;
    private String itemDesc;
    private String itemPic;
    private String Category;
    private double unitPriceSale;
    private double expectedProfit;
    private double profitMargin;
    private int status;
    private double buyPrice;
    private List<SupplierEntity> supplierEntityList;
    private List<OrderEntity> orderEntityList;
    private List<StockEntity> stockEntityList;






}