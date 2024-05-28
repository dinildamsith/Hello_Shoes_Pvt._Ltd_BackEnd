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

import java.util.List;
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
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> allCustomers = dataConvert.customerEntityListConvertCustomerDtoList(customerRepo.findAll());
        return allCustomers;
    }

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerRepo.save(dataConvert.customerDtoConvertCustomerEntity(customerDto));
    }

    @Override
    public CustomerDto searchCustomer(String searchCustomerId) {

        CustomerEntity searchCustomer = customerRepo.findById(searchCustomerId).orElse(null);

        if (searchCustomer != null) {
            return dataConvert.customerEntityConvertCustomerDto(searchCustomer);
        } else {
            System.out.println("id have no customer");
        }
        return null;
    }

    @Override
    public String updateCustomer(String updateCustomerId, CustomerDto updateCustomerDetailsDto) {

        CustomerEntity updateCustomer = customerRepo.findById(updateCustomerId).orElse(null);

        if (updateCustomer != null) {

            updateCustomer.setCustomerName(updateCustomerDetailsDto.getCustomerName());
            updateCustomer.setCustomerGender(updateCustomerDetailsDto.getCustomerGender());
            updateCustomer.setCustomerJoinDate(updateCustomer.getCustomerJoinDate());
            updateCustomer.setBirthDay(updateCustomer.getBirthDay());
            updateCustomer.setAddressLine1(updateCustomerDetailsDto.getAddressLine1());
            updateCustomer.setAddressLine2(updateCustomerDetailsDto.getAddressLine2());
            updateCustomer.setAddressLine3(updateCustomerDetailsDto.getAddressLine3());
            updateCustomer.setAddressLine4(updateCustomerDetailsDto.getAddressLine4());
            updateCustomer.setAddressLine5(updateCustomerDetailsDto.getAddressLine5());
            updateCustomer.setContactNumber(updateCustomerDetailsDto.getContactNumber());
            updateCustomer.setEmail(updateCustomerDetailsDto.getEmail());

            System.out.println(updateCustomer);
            customerRepo.save(updateCustomer);
            return "Update Customer";
        } else {
            return "This Id Have Not Customer";
        }
    }

    @Override
    public String deleteCustomer(String deleteCustomerId) {
        boolean checkIdHaveCustomer = customerRepo.existsById(deleteCustomerId);

        if (checkIdHaveCustomer) {
            customerRepo.deleteById(deleteCustomerId);
        } else {
            return "This Id Have No Customer";
        }
        return null;
    }
}
