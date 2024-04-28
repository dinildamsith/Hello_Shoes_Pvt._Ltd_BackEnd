package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ItemDto;

public interface ItemService {
    void saveItem(String itemSupplierId,ItemDto itemDto,String size,String qty);
    void updateItem(String updateItemId , ItemDto itemDto);
    void searchItem(String searchItemId , CustomerDto customerDto);
}
