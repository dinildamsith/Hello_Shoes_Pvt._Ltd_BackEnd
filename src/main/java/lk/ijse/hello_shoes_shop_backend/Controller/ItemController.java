package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.util.UtilMatters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    @RequestMapping("/save")
    void saveItem(@RequestPart("item_code") String code,
                  @RequestPart("item_desc") String desc,
                  @RequestPart("item_pic") String pic,
                  @RequestPart("category") String category,
                  @RequestPart("salePrice") String salePrice,
                  @RequestPart("expectedProfit") String expectedProfit,
                  @RequestPart("profitMargin") String profitMargin,
                  @RequestPart("status") String status,
                  @RequestPart("buyPrice") String buyPrice
                  ){
        String picConvertBase64 = UtilMatters.convertBase64(pic);
        ItemDto itemDto = new ItemDto();
        itemDto.setItemCode(code);
        itemDto.setItemDesc(desc);
        itemDto.setItemPic(picConvertBase64);
        itemDto.setCategory(category);
        itemDto.setUnitPriceSale(Double.parseDouble(salePrice));
        itemDto.setExpectedProfit(Double.parseDouble(expectedProfit));
        itemDto.setProfitMargin(Double.parseDouble(profitMargin));
        itemDto.setStatus(Integer.parseInt(status));
        itemDto.setBuyPrice(Double.parseDouble(buyPrice));

        itemService.saveItem(itemDto);
    }
}
