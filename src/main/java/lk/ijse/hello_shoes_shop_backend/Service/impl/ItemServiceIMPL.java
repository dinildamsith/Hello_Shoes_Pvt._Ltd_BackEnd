package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.ItemRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SizeRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.SupplierRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;

import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
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
    public List<ItemDto> getAllItems() {
        List<ItemEntity> all = itemRepo.findAll();
        return dataConvert.itemEntityListConvertItemDtoList(all);
    }

    @Override
    public void saveItem(String itemSupplySupplierId ,ItemDto itemDto,String size,String qty) {

        SupplierEntity supplierEntity = supplierRepo.findById(itemSupplySupplierId).orElse(null);


        if (supplierEntity != null){


            ItemEntity itemEntity = dataConvert.itemDtoConvertItemEntity(itemDto);
            StockEntity stockEntity = new StockEntity();

            stockEntity.setStockId(itemDto.getItemDesc()+ " ( size - "+size+")");
            stockEntity.setItemSize(size);
            stockEntity.setQty(qty);
            stockEntity.setItemEntititys(itemEntity);

            String haveQty = sizeRepo.checkItemHaveStock(stockEntity.getStockId());

            if (50 < Integer.parseInt(qty)){
                itemEntity.setStatus("Available");
            }else if(50 > Integer.parseInt(qty)){
                itemEntity.setStatus("Low");
            }

            if (haveQty !=null){
                int haveQtyConvert = Integer.parseInt(haveQty);
                String qty1 = stockEntity.getQty();
                int qt = Integer.parseInt(qty1);


                haveQtyConvert+=qt;
                stockEntity.setQty(String.valueOf(haveQtyConvert));
            }

            List<SupplierEntity> supplierEntityList = new ArrayList<>();
            List<ItemEntity> itemEntityList = new ArrayList<>();
            List<StockEntity> stockEntityList = new ArrayList<>();



            supplierEntityList.add(supplierEntity);
            itemEntityList.add(itemEntity);

            itemEntity.setSupplierEntityList(supplierEntityList);
            supplierEntity.setItemEntityList(itemEntityList);

             stockEntity.setItemEntititys(itemEntity);
             stockEntityList.add(stockEntity);
             stockEntityList.add(stockEntity);


            itemRepo.save(itemEntity);
            sizeRepo.save(stockEntity);

        }else{

            System.out.println("This Id Have No Supplier");
        }

    }

    @Override
    public void updateItem(String updateItemId, ItemDto updateItemDetailsDto,String supplierId) {
        ItemEntity updateItemEntity = itemRepo.findById(updateItemId).orElse(null);
        SupplierEntity supplierEntity = supplierRepo.findById(supplierId).orElse(null);

        if (updateItemEntity != null && supplierEntity !=null){


            List<SupplierEntity> supplierEntityList = new ArrayList<>();
            List<ItemEntity> itemEntityList = new ArrayList<>();

            supplierEntity.setSupplierCode(supplierId);
            updateItemEntity.setCategory(updateItemDetailsDto.getCategory());
            updateItemEntity.setBuyPrice(updateItemDetailsDto.getBuyPrice());
            updateItemEntity.setExpectedProfit(updateItemDetailsDto.getExpectedProfit());
            updateItemEntity.setItemDesc(updateItemDetailsDto.getItemDesc());
            updateItemEntity.setItemPic(updateItemDetailsDto.getItemPic());
            updateItemEntity.setProfitMargin(updateItemDetailsDto.getProfitMargin());
            updateItemEntity.setStatus(updateItemEntity.getStatus());
            updateItemEntity.setUnitPriceSale(updateItemDetailsDto.getUnitPriceSale());
            supplierEntityList.add(supplierEntity);
            itemEntityList.add(updateItemEntity);

            updateItemEntity.setSupplierEntityList(supplierEntityList);
            supplierEntity.setItemEntityList(itemEntityList);

            itemRepo.save(updateItemEntity);
        }else{
            System.out.println("this id have no Supplier");
        }
    }

    @Override
    public void deleteItem(String deleteItemId) {
        itemRepo.deleteById(deleteItemId);
    }

    @Override
    public ItemDto searchItem(String searchItemId) {
        ItemEntity itemEntity = itemRepo.findById(searchItemId).orElse(null);
        return dataConvert.itemEntityConvertItemDto(itemEntity);
    }

    @Override
    public String getLastItemId() {
        String lastItemId = itemRepo.getLastItemId();

        if (lastItemId == null){
            return "00000";
        }else{
            // Find the index of the first digit in the string
            int index = 0;
            for (int i = 0; i < lastItemId.length(); i++) {
                if (Character.isDigit(lastItemId.charAt(i))) {
                    index = i;
                    break;
                }
            }

            // Remove the first English letters
            String itemIdWithoutLetters = lastItemId.substring(index);


            return itemIdWithoutLetters;
        }

    }

    @Override
    public List<String> selectItemHasAllSizesGet(String selectItemId) {
        List<String> selectedItemHasAllSizes = itemRepo.selectItemHasAllSizesGet(selectItemId);
        return selectedItemHasAllSizes;
    }


}
