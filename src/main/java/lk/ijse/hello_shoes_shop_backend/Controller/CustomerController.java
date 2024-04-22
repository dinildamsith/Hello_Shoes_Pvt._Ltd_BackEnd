package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    CustomerService customerService;


   @ResponseStatus(HttpStatus.CREATED)
   @RequestMapping("/save")
   @PostMapping
    public String saveCustomer(@RequestBody CustomerDto customerDto){
        customerService.saveCustomer(customerDto);
        return "Save";
    }

    @GetMapping(value = "search/{id}")
    public CustomerDto searchCustomer(@PathVariable ("id") String searchCustomerId){
        CustomerDto customerDto = customerService.searchCustomer(searchCustomerId);
        return customerDto;
    }

    @PutMapping(value = "update/{id}")
    public void updateCustomer(@PathVariable ("id") String updateId , @RequestBody CustomerDto customerDto){
       customerService.updateCustomer(updateId,customerDto);
    }

}
