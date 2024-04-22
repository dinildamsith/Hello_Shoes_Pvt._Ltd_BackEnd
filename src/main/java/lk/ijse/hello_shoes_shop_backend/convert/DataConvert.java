package lk.ijse.hello_shoes_shop_backend.convert;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Dto.UserDto;
import lk.ijse.hello_shoes_shop_backend.entity.CustomerEntity;
import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lk.ijse.hello_shoes_shop_backend.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataConvert {
    private final ModelMapper modelMapper;


    // User Data
    public UserDto userEntityConvertUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
    public UserEntity userDtoConvertUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    //Customer Data
    public CustomerDto customerEntityConvertCustomerDto(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
    public CustomerEntity customerDtoConvertCustomerEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
    public List<CustomerDto> customerEntityListConvertCustomerDtoList(List<CustomerEntity> customerEntityList){
        return modelMapper.map(customerEntityList,List.class);
    }

    //Supplier Data
    public SupplierEntity supplierDtoConvertSupplierEntity(SupplierDto supplierDto){
        return modelMapper.map(supplierDto,SupplierEntity.class);
    }
    public SupplierDto supplierEntityConvertSupplierDto(SupplierEntity supplierEntity){
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }
    public List<SupplierDto> supplierEntityListConvertSupplierDtoList(List<SupplierEntity> supplierEntityList){
        return modelMapper.map(supplierEntityList, List.class);
    }
}
