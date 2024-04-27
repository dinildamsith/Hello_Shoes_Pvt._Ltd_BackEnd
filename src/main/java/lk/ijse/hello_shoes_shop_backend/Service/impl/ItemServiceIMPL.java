package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.ItemRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;
import lk.ijse.hello_shoes_shop_backend.Service.ItemService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
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
}
