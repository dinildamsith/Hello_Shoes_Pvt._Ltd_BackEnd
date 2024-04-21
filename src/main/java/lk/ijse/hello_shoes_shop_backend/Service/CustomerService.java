package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    CustomerDto searchCustomer(String searchCustomerId);
}
