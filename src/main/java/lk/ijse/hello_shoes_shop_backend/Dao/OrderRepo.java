package lk.ijse.hello_shoes_shop_backend.Dao;

import lk.ijse.hello_shoes_shop_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,String> {

    @Query(value = "SELECT orderCode FROM orders ORDER BY orderCode DESC LIMIT 1", nativeQuery = true)
    String getLastOrderId();
}
