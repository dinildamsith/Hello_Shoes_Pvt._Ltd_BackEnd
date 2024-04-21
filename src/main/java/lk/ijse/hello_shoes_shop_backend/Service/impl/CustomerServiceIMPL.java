package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.CustomerRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Service.CustomerService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
