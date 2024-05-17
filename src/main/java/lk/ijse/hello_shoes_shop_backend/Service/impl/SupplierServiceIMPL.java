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

import java.util.List;
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
    public List<SupplierDto> getAllSuppliers() {
        return dataConvert.supplierEntityListConvertSupplierDtoList(supplierRepo.findAll());
    }

    @Override
    public void saveSupplier(SupplierDto supplierDto) {
        supplierRepo.save(dataConvert.supplierDtoConvertSupplierEntity(supplierDto));
    }

    @Override
    public SupplierDto searchSupplier(String searchSupplierId) {
        boolean existsBySupplierId = supplierRepo.existsById(searchSupplierId);

        if (existsBySupplierId){
            SupplierEntity supplierEntity = supplierRepo.findById(searchSupplierId).get();
            return dataConvert.supplierEntityConvertSupplierDto(supplierEntity);
        }else {
            System.out.println("This Id Have nO Customer");
        }
        return null;
    }

    @Override
    public String updateSupplier(String updateSupplierId, SupplierDto updateSupplierDetails) {
        SupplierEntity updatedSupplierEntity = supplierRepo.findById(updateSupplierId).orElse(null);
        if (updatedSupplierEntity != null){
            updatedSupplierEntity.setSupplierName(updateSupplierDetails.getSupplierName());
            updatedSupplierEntity.setCategory(updateSupplierDetails.getCategory());
            updatedSupplierEntity.setAddressLine1(updateSupplierDetails.getAddressLine1());
            updatedSupplierEntity.setAddressLine2(updateSupplierDetails.getAddressLine2());
            updatedSupplierEntity.setAddressLine3(updateSupplierDetails.getAddressLine3());
            updatedSupplierEntity.setAddressLine4(updateSupplierDetails.getAddressLine4());
            updatedSupplierEntity.setAddressLine5(updateSupplierDetails.getAddressLine5());
            updatedSupplierEntity.setAddressLine6(updateSupplierDetails.getAddressLine6());
            updatedSupplierEntity.setContact1(updateSupplierDetails.getContact1());
            updatedSupplierEntity.setContact2(updateSupplierDetails.getContact2());
            updatedSupplierEntity.setEmail(updateSupplierDetails.getEmail());

            supplierRepo.save(updatedSupplierEntity);
            return "Update Customer";
        }else {
            return "This Id Have No Customer";
        }

    }

    @Override
    public String deleteSupplier(String deleteSupplierId) {
        boolean existsBySupplierId = supplierRepo.existsById(deleteSupplierId);
        if (existsBySupplierId){
            supplierRepo.deleteById(deleteSupplierId);
            return "Supplier DELETED";
        }else {
           return  "This Id Have No Customer";
        }

    }

}
