package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;

import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lk.ijse.hello_shoes_shop_backend.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    DataConvert dataConvert;



    @PostMapping
    @RequestMapping("/save/{id}/{size}/{qty}")
    void saveItem(@PathVariable("id") String supplierId,
                  @PathVariable("size") String size,
                  @PathVariable("qty") String qty,
                  @RequestPart("item_code") String code,
                  @RequestPart("item_desc") String desc,
                  @RequestPart("item_pic") String pic,
                  @RequestPart("category") String category,
                  @RequestPart("salePrice") String salePrice,
                  @RequestPart("expectedProfit") String expectedProfit,
                  @RequestPart("profitMargin") String profitMargin,
                  @RequestPart("status") String status,
                  @RequestPart("buyPrice") String buyPrice

                  ){


        ItemDto itemDto = new ItemDto();


        String picConvertBase64 = UtilMatters.convertBase64(pic);
        itemDto.setItemCode(code);
        itemDto.setItemDesc(desc);
        itemDto.setItemPic(picConvertBase64);
        itemDto.setCategory(category);
        itemDto.setUnitPriceSale(Double.parseDouble(salePrice));
        itemDto.setExpectedProfit(Double.parseDouble(expectedProfit));
        itemDto.setProfitMargin(Double.parseDouble(profitMargin));
        itemDto.setStatus(Integer.parseInt(status));
        itemDto.setBuyPrice(Double.parseDouble(buyPrice));

        itemService.saveItem(supplierId,itemDto,size,qty);


    }


    @PutMapping
    @RequestMapping("/update/{id}/{supplierId}/{size}/{qty}")
    void updateItem(@PathVariable("id") String updateItemId ,
                    @PathVariable("supplierId") String supplierId,
                    @PathVariable("size") String size,
                    @PathVariable("qty") String qty,
                    @RequestPart("item_desc") String desc,
                    @RequestPart("item_pic") String pic,
                    @RequestPart("category") String category,
                    @RequestPart("salePrice") String salePrice,
                    @RequestPart("expectedProfit") String expectedProfit,
                    @RequestPart("profitMargin") String profitMargin,
                    @RequestPart("status") String status,
                    @RequestPart("buyPrice") String buyPrice
    ){


        ItemDto itemDto = new ItemDto();


        String picConvertBase64 = UtilMatters.convertBase64(pic);
        itemDto.setItemDesc(desc);
        itemDto.setItemPic(picConvertBase64);
        itemDto.setCategory(category);
        itemDto.setUnitPriceSale(Double.parseDouble(salePrice));
        itemDto.setExpectedProfit(Double.parseDouble(expectedProfit));
        itemDto.setProfitMargin(Double.parseDouble(profitMargin));
        itemDto.setStatus(Integer.parseInt(status));
        itemDto.setBuyPrice(Double.parseDouble(buyPrice));


        itemService.updateItem(updateItemId , itemDto,supplierId,size,qty);
    }
}
