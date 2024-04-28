package lk.ijse.hello_shoes_shop_backend.Service.impl;

import jakarta.persistence.Id;
import lk.ijse.hello_shoes_shop_backend.Dao.CustomerRepo;
import lk.ijse.hello_shoes_shop_backend.Dao.OrderRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Service.OrderService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    DataConvert dataConvert;

    @Override
    public void saveOrder(OrderDto orderDto) {

        OrderEntity orderEntity = dataConvert.orderDtoConvertOrderEntity(orderDto);
        ItemEntity itemEntity = new ItemEntity();

        String customerCode = orderDto.getCustomerDetails().getCustomerCode();
        CustomerEntity customerEntity = customerRepo.findById(customerCode).orElse(null);
        int totalPoints = customerEntity.getTotalPoints();

        int qty = orderDto.getQty();
        double unitPrice = orderDto.getUnitPrice();
        double orderTotal = qty*unitPrice;

        orderEntity.setTotal(orderTotal);
       if (800<orderTotal){
            orderEntity.setPoints(1);
            totalPoints+=1;
            customerEntity.setTotalPoints(totalPoints);
            customerRepo.save(customerEntity);

       }

        ItemEntity itemEntity1 = orderDto.getBuyItem().get(0);
        itemEntity.setItemCode(itemEntity1.getItemCode());

        List<OrderEntity> orderEntityList = new ArrayList<>();
        List<ItemEntity> itemEntityList = new ArrayList<>();

        orderEntityList.add(orderEntity);
        itemEntityList.add(itemEntity);

        orderEntity.setBuyItem(itemEntityList);
        itemEntity.setOrder(orderEntityList);


        orderRepo.save(orderEntity);

    }
}
