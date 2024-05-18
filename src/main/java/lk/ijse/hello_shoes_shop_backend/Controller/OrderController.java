package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import lk.ijse.hello_shoes_shop_backend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @RequestMapping("/save")
    public void saveOrder(@RequestBody OrderDto orderDto){
        System.out.println(orderDto);
        orderService.saveOrder(orderDto);
    }

    @PostMapping
    @RequestMapping("/return")
    public void returnOrder(@RequestBody ReturnDto returnDto){
        orderService.returnOrder(returnDto);
    }


    @GetMapping
    @RequestMapping("/newOrderId")
    public String nextOrderIdGet(){
        return orderService.getLastOrderId();
    }

    @GetMapping
    @RequestMapping("/getAllOrders")
    public List<OrderDto> getAllOrders(){
        List<OrderDto> allOrders = orderService.getAllOrders();
        return allOrders;
    }

    @GetMapping
    @RequestMapping("/returnNextId")
    public String getReturnOrderIdNext(){
        String nextReturnId = orderService.getNextReturnId();
        return nextReturnId;
    }

}
