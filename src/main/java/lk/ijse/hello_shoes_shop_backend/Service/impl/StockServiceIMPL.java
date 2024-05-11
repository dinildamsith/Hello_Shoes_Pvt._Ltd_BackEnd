package lk.ijse.hello_shoes_shop_backend.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.hello_shoes_shop_backend.Dao.SizeRepo;
import lk.ijse.hello_shoes_shop_backend.Service.StockService;
import lk.ijse.hello_shoes_shop_backend.entity.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StockServiceIMPL implements StockService {
    @Autowired
    SizeRepo sizeRepo;
    @Override
    public List<StockEntity> getAllStock() {
        List<StockEntity> allStock = sizeRepo.findAll();
        return allStock;
    }
}
