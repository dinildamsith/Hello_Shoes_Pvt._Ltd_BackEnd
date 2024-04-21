package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.CustomerRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Service.CustomerService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    DataConvert dataConvert;
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerRepo.save(dataConvert.customerDtoConvertCustomerEntity(customerDto));
    }

    @Override
    public CustomerDto searchCustomer(String searchCustomerId) {

        CustomerEntity searchCustomer = customerRepo.findById(searchCustomerId).orElse(null);

        if (searchCustomer != null){
            return dataConvert.customerEntityConvertCustomerDto(searchCustomer);
        }else{
            System.out.println("id have no customer");
        }
        return null;
    }
}
