package lk.ijse.hello_shoes_shop_backend.Service.impl;

import jakarta.persistence.Id;
import lk.ijse.hello_shoes_shop_backend.Dao.*;
import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import lk.ijse.hello_shoes_shop_backend.Service.DateServices;
import lk.ijse.hello_shoes_shop_backend.Service.OrderService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    ReturnRepo returnRepo;
    @Autowired
    SizeRepo sizeRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    DateServices dateServices;

    @Override
    public void saveOrder(OrderDto orderDto) {

        OrderEntity orderEntity = dataConvert.orderDtoConvertOrderEntity(orderDto);
        orderEntity.setOrderStatus("ORDER CONFIRM");
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
        ItemEntity itemEntity2 = orderDto.getBuyItem().get(0);
        System.out.println(itemEntity2);
       StockEntity stockEntity = sizeRepo.getItemQty(itemEntity2.getItemCode(), String.valueOf(orderDto.getSize()));
        System.out.println(stockEntity.getQty());
        int i = Integer.parseInt(stockEntity.getQty());
        int x = i-orderDto.getQty();
        stockEntity.setQty(String.valueOf(x));

        sizeRepo.save(stockEntity);


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

    @Override
    public void returnOrder(ReturnDto returnDto) {

        OrderEntity orderEntity = orderRepo.findById(returnDto.getOrderEntity().getOrderCode()).orElse(null);
        Date orderPurchaseDate = orderEntity.getPurchaseDate();

        Date currentDate = new Date();
        LocalDate orderReturnLstDay = dateServices.orderLastReturnDay(orderPurchaseDate);
        LocalDate todayDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        if (orderReturnLstDay.isBefore(todayDate)) {

            System.out.println("Sorry Customer This Order Can't return you item return last day missing !!1");

        } else{

            String orderStatus = orderEntity.getOrderStatus();

            if (orderStatus.equals("ORDER CONFIRM")){

                returnRepo.save(dataConvert.returnDtoConvertReturnEntity(returnDto));

                OrderEntity placeOrde = orderRepo.getReferenceById(returnDto.getOrderEntity().getOrderCode());
                System.out.println(placeOrde.getPurchaseDate());

                placeOrde.setOrderStatus("ORDER RETURN");
                orderRepo.save(placeOrde);

                StockEntity itemQty = sizeRepo.getItemQty(returnDto.getItemId(), returnDto.getSize());
                int qty = Integer.parseInt(itemQty.getQty());
                int updateQty = qty + Integer.parseInt(returnDto.getQty());
                itemQty.setQty(String.valueOf(updateQty));
                sizeRepo.save(itemQty);

            }else {
                System.out.println("Sorry Sir You Can order Return One Chanes Only");
            }

        }

    }
}
