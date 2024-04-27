package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements SuperEntity{
    @Id
    private String orderCode;
    private Date purchaseDate;
    private String customerName;
    private String itemDesc;
    private int size;
    private double unitPrice;
    private int qty;
    private double total;
    private String paymentMethod;
    private double points;
    private String cashierName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customerDetails;

    @ManyToMany
    private List<ItemEntity> buyItem;

    @ManyToOne
    @JoinColumn(name = "order_sale_EmployeeId")
    private UserEntity userEntity;

}
