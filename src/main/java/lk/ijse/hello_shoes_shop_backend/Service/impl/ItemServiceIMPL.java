package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.ItemRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SupplierRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    DataConvert dataConvert;
    @Autowired
    SupplierRepo supplierRepo;
    @Override
    public void saveItem(String itemSupplySupplierId ,ItemDto itemDto) {

        SupplierEntity supplierEntity = supplierRepo.findById(itemSupplySupplierId).orElse(null);
        if (supplierEntity !=null){

            ItemEntity itemEntity = dataConvert.itemDtoConvertItemEntity(itemDto);

            List<SupplierEntity> supplierEntityList = new ArrayList<>();
            List<ItemEntity> itemEntityList = new ArrayList<>();

            supplierEntityList.add(supplierEntity);
            itemEntityList.add(itemEntity);

            itemEntity.setSupplierEntityList(supplierEntityList);
            supplierEntity.setItemEntityList(itemEntityList);

            itemRepo.save(itemEntity);

        }else{
            System.out.println("This Id Have No Suppliers");
        }

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

    @Override
    public void searchItem(String searchItemId, CustomerDto customerDto) {

    }
}
