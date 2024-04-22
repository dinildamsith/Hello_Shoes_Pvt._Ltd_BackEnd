package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    @RequestMapping("/save")
    public void saveSupplier(@RequestBody SupplierDto supplierDto){
        supplierService.saveSupplier(supplierDto);
    }

    @GetMapping
    @RequestMapping("/search/{id}")
    public SupplierDto searchSupplier(@PathVariable ("id") String searchSupplierId){
        SupplierDto searchSupplierDto = supplierService.searchSupplier(searchSupplierId);
        return searchSupplierDto;
    }

}
