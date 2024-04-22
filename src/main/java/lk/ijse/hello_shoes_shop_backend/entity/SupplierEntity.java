package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.hello_shoes_shop_backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class SupplierEntity implements SuperEntity{
    @Id
    private String supplierCode;
    private String supplierName;
    private Category category;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String addressLine6;
    private String contact1;
    private String contact2;
    private String email;

}
