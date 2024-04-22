package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.SupplierRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.SupplierService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceIMPL implements SupplierService {

    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    DataConvert dataConvert;
    @Override
    public void saveSupplier(SupplierDto supplierDto) {
        supplierRepo.save(dataConvert.supplierDtoConvertSupplierEntity(supplierDto));
    }

    @Override
    public SupplierDto searchSupplier(String searchSupplierId) {
        boolean existsBySupplierId = supplierRepo.existsById(searchSupplierId);

        if (existsBySupplierId){
            return dataConvert.supplierEntityConvertSupplierDto(supplierRepo.getReferenceById(searchSupplierId));
        }else {
            System.out.println("This Id Have nO Customer");
        }
        return null;
    }
}
