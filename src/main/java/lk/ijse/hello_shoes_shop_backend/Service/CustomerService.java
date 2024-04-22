package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import org.springframework.http.HttpStatus;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    CustomerDto searchCustomer(String searchCustomerId);
    String updateCustomer(String updateCustomerId, CustomerDto updateCustomerDetailsDto);
    String deleteCustomer(String deleteCustomerId);
}
