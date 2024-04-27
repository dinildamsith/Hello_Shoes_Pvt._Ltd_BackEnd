package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size")
public class SizeEntity implements SuperEntity{

    @Id
    private String itemSize;

//    @ManyToMany(mappedBy = "size")
//    private List<ItemEntity> item;

    @OneToMany(mappedBy = "size")
    private List<StockEntity> stock;
}
