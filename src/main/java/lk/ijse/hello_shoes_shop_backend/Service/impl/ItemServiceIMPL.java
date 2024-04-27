package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.ItemRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    DataConvert dataConvert;
    @Override
    public void saveItem(ItemDto itemDto) {
        itemRepo.save(dataConvert.itemDtoConvertItemEntity(itemDto));
    }

    @Override
    public void updateItem(String updateItemId, ItemDto updateItemDetailsDto) {
        ItemEntity updateItemEntity = itemRepo.findById(updateItemId).orElse(null);
        if (updateItemEntity != null){
            updateItemEntity.setCategory(updateItemDetailsDto.getCategory());
            updateItemEntity.setBuyPrice(updateItemDetailsDto.getBuyPrice());
            updateItemEntity.setExpectedProfit(updateItemDetailsDto.getExpectedProfit());
            updateItemEntity.setItemDesc(updateItemDetailsDto.getItemDesc());
            updateItemEntity.setItemPic(updateItemDetailsDto.getItemPic());
            updateItemEntity.setProfitMargin(updateItemDetailsDto.getProfitMargin());
            updateItemEntity.setStatus(updateItemEntity.getStatus());
            updateItemEntity.setUnitPriceSale(updateItemDetailsDto.getUnitPriceSale());

            itemRepo.save(updateItemEntity);
        }else{
            System.out.println("this id have no customer");
        }
    }
}
