package lk.ijse.hello_shoes_shop_backend.Dao;

import lk.ijse.hello_shoes_shop_backend.entity.ItemEntity;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,String> {



}
