package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.ItemRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SizeRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SupplierRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.SizeEntity;

import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    DataConvert dataConvert;
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    SizeRepo sizeRepo;
    @Override
    public void saveItem(String itemSupplySupplierId ,ItemDto itemDto,String size,String qty) {

        SupplierEntity supplierEntity = supplierRepo.findById(itemSupplySupplierId).orElse(null);


        if (supplierEntity != null){


            ItemEntity itemEntity = dataConvert.itemDtoConvertItemEntity(itemDto);
            SizeEntity sizeEntity = new SizeEntity();

            sizeEntity.setStockId(String.valueOf(UUID.randomUUID()));
            sizeEntity.setItemSize(size);
            sizeEntity.setQty(qty);
            sizeEntity.setItemEntititys(itemEntity);


            List<SupplierEntity> supplierEntityList = new ArrayList<>();
            List<ItemEntity> itemEntityList = new ArrayList<>();
            List<SizeEntity> sizeEntityList = new ArrayList<>();



            supplierEntityList.add(supplierEntity);
            itemEntityList.add(itemEntity);

            itemEntity.setSupplierEntityList(supplierEntityList);
            supplierEntity.setItemEntityList(itemEntityList);

             sizeEntity.setItemEntititys(itemEntity);
             sizeEntityList.add(sizeEntity);
             sizeEntityList.add(sizeEntity);


            itemRepo.save(itemEntity);
            sizeRepo.save(sizeEntity);

        }else{
            System.out.println("This Id Have No Supplier");
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
