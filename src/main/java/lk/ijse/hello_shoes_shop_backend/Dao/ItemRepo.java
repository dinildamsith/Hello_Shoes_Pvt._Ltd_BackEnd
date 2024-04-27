package lk.ijse.hello_shoes_shop_backend.Dao;

import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ItemEntity,String> {
}
