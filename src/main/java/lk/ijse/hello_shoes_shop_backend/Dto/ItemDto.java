package lk.ijse.hello_shoes_shop_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String supplierName;
    private double buyPrice;
}
