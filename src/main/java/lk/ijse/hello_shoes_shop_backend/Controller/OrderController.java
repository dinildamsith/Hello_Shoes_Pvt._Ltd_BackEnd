package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.OrderDto;
import lk.ijse.hello_shoes_shop_backend.Dto.ReturnDto;
import lk.ijse.hello_shoes_shop_backend.Service.OrderService;
import lk.ijse.hello_shoes_shop_backend.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.PATCH, RequestMethod.DELETE ,RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET})
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
    public String returnOrder(@RequestBody ReturnDto returnDto){
        String massage = orderService.returnOrder(returnDto);
        return massage;
    }


    @GetMapping
    @RequestMapping("/newOrderId")
    public String nextOrderIdGet(){
        return orderService.getLastOrderId();
    }

    @GetMapping
    @RequestMapping("/getAllOrders")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping
    @RequestMapping("/searchOrder/{id}")
    public OrderDto searchOrder(@PathVariable ("id") String searchOrderId){
        OrderDto orderDto = orderService.searchOrder(searchOrderId);
        return orderDto;
    }

    @GetMapping
    @RequestMapping("/getTotalSale/{date}")
    public Double  getTotal(@PathVariable ("date") String date){
        Double totalSaleSelectDate = orderService.totalSaleGet(date);
        return totalSaleSelectDate;
    }

    @GetMapping
    @RequestMapping("/branchWiseOrderDetailsGet/{branch}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDto> branchWiseOrderDetailsGet(@PathVariable ("branch") String branch){
        List<OrderDto> orderDtos = orderService.branchWiseOrderDetailsGet(branch);
        return orderDtos;
    }

    @GetMapping
    @RequestMapping("/employeeSaleOrders/{mail}")
    @PreAuthorize("hasRole('USER')")
    public List<OrderDto> employeeSaleAllOrdersGet(@PathVariable ("mail") String empMail) {
        List<OrderDto> employeeSaleOrders = orderService.employeeSaleAllOrdersGet(empMail);
        return employeeSaleOrders;
    }

    //TODO
//    @GetMapping
//    @RequestMapping("/saleMostItem")
//    public String getMostSaleItemImg(@RequestParam("date") String date) {
////        String mostSaleItemGet = orderService.mostSaleItemGet();
//        return mostSaleItemGet;
//    }
}
