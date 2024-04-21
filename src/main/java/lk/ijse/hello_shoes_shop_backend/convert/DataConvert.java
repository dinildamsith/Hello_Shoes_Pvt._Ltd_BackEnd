package lk.ijse.hello_shoes_shop_backend.convert;

import lk.ijse.hello_shoes_shop_backend.Dto.CustomerDto;
import lk.ijse.hello_shoes_shop_backend.Dto.UserDto;
import lk.ijse.hello_shoes_shop_backend.entity.CustomerEntity;
import lk.ijse.hello_shoes_shop_backend.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataConvert {
    private final ModelMapper modelMapper;

    public UserDto userEntityConvertUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity userDtoConvertUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    public CustomerDto customerEntityConvertCustomerDto(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDto.class);
    }

    public CustomerEntity customerDyoConvertCustomerEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
}
