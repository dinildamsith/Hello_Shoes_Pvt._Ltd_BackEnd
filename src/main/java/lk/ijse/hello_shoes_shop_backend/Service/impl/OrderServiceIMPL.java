package lk.ijse.hello_shoes_shop_backend.Service.impl;

import jakarta.persistence.Id;
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
    DataConvert dataConvert;

    @Override
    public void saveOrder(OrderDto orderDto) {

        OrderEntity orderEntity = dataConvert.orderDtoConvertOrderEntity(orderDto);
        ItemEntity itemEntity = new ItemEntity();

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
